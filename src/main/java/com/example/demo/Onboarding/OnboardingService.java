package com.example.demo.Onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OnboardingService {

    private final OnboardingRepository onboardingRepository;

    @Autowired
    public OnboardingService(OnboardingRepository onboardingRepository) {
        this.onboardingRepository = onboardingRepository;
    }

    public List<Employee> getEmployees() {
        return onboardingRepository.findAll();
    }
}
