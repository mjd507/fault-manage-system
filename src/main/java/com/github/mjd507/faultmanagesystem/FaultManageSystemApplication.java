package com.github.mjd507.faultmanagesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.mjd507.faultmanagesystem.mapper")
public class FaultManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaultManageSystemApplication.class, args);
    }

}
