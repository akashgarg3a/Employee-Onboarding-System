package com.example.demo.Onboarding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OnboardingServiceTest {

    @Mock
    private OnboardingRepository onboardingRepository;
    private OnboardingService underTest;


    @BeforeEach
    void setUp() {
        underTest = new OnboardingService(onboardingRepository);
    }

    @Test
    void checkFunGetEmployees() {
        //when
        underTest.getEmployees();

        // then
        verify(onboardingRepository).findAll();

    }

    @Test
    void addNewEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void updateEmployee() {
    }
}