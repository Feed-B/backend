package com.example.team_12_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Team12BeApplication {
    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:nginx-set.yml";
    public static void main(String[] args) {

        new SpringApplicationBuilder(Team12BeApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
