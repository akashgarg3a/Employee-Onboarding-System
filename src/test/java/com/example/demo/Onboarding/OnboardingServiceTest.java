package com.example.demo.Onboarding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OnboardingServiceTest {

    @Mock
    private OnboardingRepository onboardingRepository;
    private OnboardingService underTest;

    @Mock
    private Employee employee;

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
        verify(onboardingRepository, times(1)).findEmailExist(emp.getEmail());
        verify(onboardingRepository, times(1)).save(any(Employee.class));

        Employee captureEmployee = employeeArgumentCaptor.getValue();

        assertThat(captureEmployee).isEqualTo(emp);
    }

    @Test
    void checkAddNewEmployeeThrowException() {
        // given
        Employee emp = new Employee(
                "Akash",
                "Garg",
                "",
                "akash.garg@gmail.com",
                LocalDate.of(1996, 11, 15)
        );

        // when
        given(onboardingRepository.findEmailExist(anyString()) ).willReturn(true);

        // then
        assertThatThrownBy(() -> underTest.addNewEmployee(emp) ).isInstanceOf(IllegalStateException.class).hasMessageContaining("Email already Exit");
        verify(onboardingRepository, never()).save(any());
    }

    @Test
    void checkDeleteEmployee() {
        // given
        Employee emp = new Employee(
                "Akash",
                "Garg",
                "",
                "akash.garg@gmail.com",
                LocalDate.of(1996, 11, 15)
        );
        long id = 1;

        // when
        Mockito.when(onboardingRepository.findById(id)).thenReturn(Optional.of(emp));

        // then
        underTest.deleteEmployee(id);
        verify(onboardingRepository, times(1)).findById(id);
        verify(onboardingRepository, times(1)).deleteById(id);
    }

    @Test
    void checkDeleteEmployeeThrowException() {
        //given
        long id = 1;

        //when
        //then
        assertThatThrownBy(()-> underTest.deleteEmployee(id)).isInstanceOf(IllegalStateException.class).hasMessageContaining("No Employee with Id Found");
        verify(onboardingRepository, never()).deleteById(id);
    }

    @Test
    void updateEmployee() {
        // given
        Employee emp = new Employee(
                "Akash",
                "Garg",
                "",
                "akash.garg@gmail.com",
                LocalDate.of(1996, 11, 15)
        );
        long id = 1;
        String firstName = "ankush";
        String email = "ankush.garg@gmail.com";
        emp.setId(id);

        // when
        Mockito.when(onboardingRepository.findById(id)).thenReturn(Optional.of(emp));
        underTest.updateEmployee(id, firstName, email);

        // then
        assert(emp.getEmail() == email);
        assert(emp.getFirstName() == firstName);

    }

    @Test
    void checkUpdateEmployeeThrowExceptionForId() {
        //given
        long id = 1;
        String firstName = "ankush";
        String email = "ankush.garg@gmail.com";

        //when
        //then
        assertThatThrownBy(()-> underTest.updateEmployee(id, firstName, email)).isInstanceOf(IllegalStateException.class).hasMessageContaining("id not found");
//        verify(onboardingRepository, never()).deleteById(1l);
    }

    @Test
    void checkUpdateEmployeeThrowExceptionForEmail() {
        // given
        Employee emp = new Employee(
                "Akash",
                "Garg",
                "",
                "akash.garg@gmail.com",
                LocalDate.of(1996, 11, 15)
        );
        long id =1;
        emp.setId(id);
        String firstName = "ankush";
        String email = "ankush.garg@gmail.com";

        // when
        Mockito.when(onboardingRepository.findById(id)).thenReturn(Optional.of(emp));
        Mockito.when(onboardingRepository.findEmailExist(email)).thenReturn(true);

        // then
        assertThatThrownBy(() -> underTest.updateEmployee(1l,firstName, email) ).isInstanceOf(IllegalStateException.class).hasMessageContaining("email already taken");
    }
}