package com.example.demo.Onboarding;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("/Employee")
public class OnboardingController {

    private final OnboardingService onboardingService;

    @Autowired
    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @GetMapping("/AllEmployees")
    public List<Employee> AllEmployees() {
        return onboardingService.getEmployees();
    }

    @PostMapping("/NewEmployee")
    public void addEmployee(@RequestBody Employee employee) {
        onboardingService.addNewEmployee(employee);
    }

    @DeleteMapping(path = "{EmployeeID}")
    public void deleteEmployee(@PathVariable("EmployeeID") Long id) {
        onboardingService.deleteEmployee(id);
    }
}
