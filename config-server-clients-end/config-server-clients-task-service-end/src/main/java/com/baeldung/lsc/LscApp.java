package com.baeldung.lsc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class LscApp {

    public static void main(final String... args) {
        SpringApplication.run(LscApp.class, args);
    }

    @Bean
    public CommandLineRunner logProperties(@Value("${global.message:}") String globalMsg,
            @Value("${custom.property:}") String customProp) {
        return args -> {
            System.out.println("Global Property: " + globalMsg);
            System.out.println("Service Property: " + customProp);
        };
    }

}
