package vn.slf.handlers;

import vn.slf.functions.TaskFunction;
import vn.slf.models.TaskRequest;
import vn.slf.models.TaskResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication(scanBasePackages = {"com.slf"})
public class FunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }

    @Bean
    public Function<TaskRequest, TaskResponse> handle() {
        return new TaskFunction();
    }
}
