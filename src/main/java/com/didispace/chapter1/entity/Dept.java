package com.didispace.chapter1.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Dept {
    @Id
    private Integer deptNo;
    private String deptName;
    private String deptDesc;

    public Dept(String deptName, String deptDesc) {
        this.deptName = deptName;
        this.deptDesc = deptDesc;
    }
}
