package com.example.demo.Onboarding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingRepository extends JpaRepository<Employee, Long> {
}
