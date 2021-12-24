package com.example.demo.Onboarding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OnboardingRepositoryTest {

    @Autowired
    private OnboardingRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void CheckIfEmailExists() {
        // given
        String email = "akash.garg@gmail.com";
        Employee emp = new Employee(
                "Akash",
                "Garg",
                "",
                email,
                LocalDate.of(1996, 11, 15)
        );
        underTest.save(emp);

        // when
        boolean expected = underTest.findEmployeeByEmail(email).isPresent();

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void CheckIfEmailNotExists() {
        // given
        String email = "akash.garg@gmail.com";

        // when
        boolean expected = underTest.findEmployeeByEmail(email).isPresent();

        // then
        assertThat(expected).isFalse();
    }

}