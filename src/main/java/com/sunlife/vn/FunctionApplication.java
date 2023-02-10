package com.sunlife.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }

//    @Bean
//    public Function<TaskRequest, TaskResponse> handle() {
//        return new TaskFunction();
//    }
}
