package com.example.team_12_be;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Team12BeApplicationTests {

    @BeforeAll
    static void setup() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET", "default_jwt_secret"));
    }
    @Test
    void contextLoads() {

    }

}
