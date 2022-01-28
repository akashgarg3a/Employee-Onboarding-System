package com.example.demo.Onboarding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class OnboardingControllerTest {

    @Mock
    private OnboardingService onboardingService;
    private OnboardingController underTest;

    @BeforeEach
    void setUp() {
        underTest = new OnboardingController(onboardingService);
    }

    @Test
    void allEmployeeTest() {
        // given
        // when
        // then
        underTest.AllEmployees();
        verify(onboardingService, times(1)).getEmployees();
    }
}
