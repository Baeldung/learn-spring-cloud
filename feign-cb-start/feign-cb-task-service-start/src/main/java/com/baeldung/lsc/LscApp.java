package com.baeldung.lsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LscApp {

    public static void main(final String... args) {
        SpringApplication.run(LscApp.class, args);
    }

}
