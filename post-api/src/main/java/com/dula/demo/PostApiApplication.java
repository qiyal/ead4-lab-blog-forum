package com.dula.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PostApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostApiApplication.class, args);
    }

}
