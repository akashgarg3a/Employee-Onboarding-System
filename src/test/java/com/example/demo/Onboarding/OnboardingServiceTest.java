package com.example.demo.Onboarding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void checkGetEmployees() {
        //when
        underTest.getEmployees();

        // then
        verify(onboardingRepository).findAll();
    }

    @Test
    void checkAddNewEmployee() {
        // given
        Employee emp = new Employee(
                "Akash",
                "Garg",
                "",
                "akash.garg@gmail.com",
                LocalDate.of(1996, 11, 15)
        );

        // when
        underTest.addNewEmployee(emp);

        // then
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(onboardingRepository).save(employeeArgumentCaptor.capture());

        Employee captureEmployee = employeeArgumentCaptor.getValue();

        assertThat(captureEmployee).isEqualTo(emp);
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void updateEmployee() {
    }
}