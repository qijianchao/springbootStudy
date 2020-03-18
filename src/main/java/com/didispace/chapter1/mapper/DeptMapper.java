package com.didispace.chapter1.mapper;

import com.didispace.chapter1.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    int insert(@Param("deptNo") Integer deptNo, String deptName, String deptDesc);
    void delete(Integer deptNo);
    void update(Dept dept);
    List<Dept> findAll();
    List<Dept> findByNo(Integer deptNo);
    Dept findByName(String deptName);
}
