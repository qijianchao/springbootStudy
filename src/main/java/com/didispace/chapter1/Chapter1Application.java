package com.didispace.chapter1;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@MapperScan("com.didispace.chapter1.mapper")
@SpringBootApplication
public class Chapter1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter1Application.class, args);
    }

}

