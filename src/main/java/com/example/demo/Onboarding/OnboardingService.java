package com.example.demo.Onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        boolean duplicate = onboardingRepository.findEmailExist(employee.getEmail());
        if(duplicate) {
            throw new IllegalStateException("Email already Exit");
        }
        onboardingRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if(onboardingRepository.findById(id).isEmpty()) {
            throw new IllegalStateException("No Employee with Id Found");
        }
        onboardingRepository.deleteById(id);
    }

    @Transactional
    public void updateEmployee(Long id, String firstname, String email) {
        Employee emp = onboardingRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("id not found")
        );
        if(firstname != null && firstname.length() > 0 && firstname != emp.getFirstName())
            emp.setFirstName(firstname);
        if(email != null && email.length() > 0) {
            if(onboardingRepository.findEmailExist(email))
                throw new IllegalStateException("email already taken");
            emp.setEmail(email);
        }
    }
}
