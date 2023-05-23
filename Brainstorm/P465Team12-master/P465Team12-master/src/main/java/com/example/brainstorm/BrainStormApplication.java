package com.example.brainstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class BrainStormApplication {

    //added comment
    public static void main(String[] args) {
        SpringApplication.run(BrainStormApplication.class, args);
    }
}
