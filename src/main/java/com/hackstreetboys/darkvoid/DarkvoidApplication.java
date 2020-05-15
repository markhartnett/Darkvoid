package com.hackstreetboys.darkvoid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DarkvoidApplication {
    public static void main(String[] args) {
        SpringApplication.run(DarkvoidApplication.class, args);
    }

}
