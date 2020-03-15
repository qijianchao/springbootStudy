package com.didispace.chapter1.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@ApiModel(description = "用户实体")
public class User {
    @NotNull
    @ApiModelProperty("编号")
    private Long id;

    @NotNull
    @ApiModelProperty("姓名")
    private String name;

    @NotNull
    @Min(5)
    @Max(60)
    @ApiModelProperty("年龄")
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
