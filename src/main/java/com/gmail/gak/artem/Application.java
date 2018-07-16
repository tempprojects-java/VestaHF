package com.gmail.gak.artem;

import com.gmail.gak.artem.app.DataGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(DataGenerator dataGenerator) {
        return (String... strings) -> {
            dataGenerator.init();
        };
    }
}
