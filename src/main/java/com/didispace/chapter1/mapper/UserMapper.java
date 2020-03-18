package com.didispace.chapter1.mapper;

import com.didispace.chapter1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO USER(NAME,AGE) VALUES(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertByUser(User user);

    @Update("UPDATE USER SET name=#{name},age=#{age} where id=#{id}")
    void update(User user);

    @Delete("DELETE FROM USER WHERE id=#{id}")
    void delete(Long id);

    @Delete("DELETE FROM USER")
    void deleteAll();

    @Select("SELECT * FROM USER")
    List<User> findAll();
}
