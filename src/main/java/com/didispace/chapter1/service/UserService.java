package com.didispace.chapter1.service;

import com.didispace.chapter1.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 创建一个用户
     * @param name
     * @param age
     * @return 用户主键id
     */
    int create(String name, Integer age);

    /**
     * 通过主键获得一个用户
     * @param id
     * @return
     */
    User getById(Long id);

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUsers();

    /**
     * 删除所有用户
     * @return
     */
    int deleteAllUsers();
}
