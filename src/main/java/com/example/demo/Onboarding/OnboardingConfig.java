package com.example.demo.Onboarding;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class OnboardingConfig {

    @Bean
    CommandLineRunner commandLineRunner( OnboardingRepository repository) {
        return args -> {
            Employee Akash = new Employee(
                    "Akash",
                    "Garg",
                    "",
                    "akash.garg@gmail.com",
                    LocalDate.of(1998, 9, 19)
            );
            Employee Piyush = new Employee(
                    "Piyush",
                    "Saini",
                    "",
                    "piyush.saini@gmail.com",
                    LocalDate.of(1997, 9, 29)
            );
            repository.saveAll(
                    List.of(Akash, Piyush)
            );
        };
    }
}
