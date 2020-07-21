package com.kingc.safetystandard.assessstandardtool;


import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kingc.safetystandard.assessstandardtool.dao.**")
public class AssessstandardtoolApplication {

    public static void main(String[] args){
        SpringApplication.run(AssessstandardtoolApplication.class, args);
    }
}
