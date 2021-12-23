package com.example.demo.Onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public void addNewEmployee(Employee employee) {
        System.out.println(employee);
        Optional<Employee> duplicate = onboardingRepository.findEmployeeByEmail(employee.getEmail());
        if(duplicate.isPresent()) {
            throw new IllegalStateException("Email already Exit");
        }
        onboardingRepository.save(employee);
    }
}
