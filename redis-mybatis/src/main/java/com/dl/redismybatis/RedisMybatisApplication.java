package com.dl.redismybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan(basePackages = "com.dl.redismybatis.mapper")
public class RedisMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisMybatisApplication.class, args);
    }
}
