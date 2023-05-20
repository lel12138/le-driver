package com.wule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.wule.mapper")
@EnableCaching
public class WuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuleApplication.class, args);
    }

}
