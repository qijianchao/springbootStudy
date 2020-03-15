package com.didispace.chapter1.service;

import com.didispace.chapter1.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private JdbcTemplate jdbcTemplate;

    UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(String name, Integer age) {
        jdbcTemplate.update("insert into USER(NAME,AGE) VALUES (?,?)", name, age);
        return 1;
    }

    @Override
    public User getById(Long id) {
        List<User> users = jdbcTemplate.query("select ID, NAME, AGE from USER where ID = ?", (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setName(resultSet.getString("NAME"));
            user.setAge(resultSet.getInt("AGE"));
            return user;
        }, id);
        return users.get(0);
    }

    @Override
    public boolean deleteById(Long id) {
        int ret = jdbcTemplate.update("delete from USER where ID = ?", id);
        if(ret == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query("select ID, NAME, AGE from USER ", (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setName(resultSet.getString("NAME"));
            user.setAge(resultSet.getInt("AGE"));
            return user;
        });
        return users;
    }

    @Override
    public int deleteAllUsers() {
        return jdbcTemplate.update("delete from USER");
    }
}
