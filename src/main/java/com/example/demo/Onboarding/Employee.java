package com.example.demo.Onboarding;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Employee {
    @Id
    @SequenceGenerator(
            name = "Employee_sequence",
            sequenceName = "Employee_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Employee_sequence"
    )
    private Long id;
    private String firstName, lastName, middleName, fullName;
    private String email;
    private LocalDate DOB;
    private Integer age;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, String middleName, String email, LocalDate DOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.DOB = DOB;
        setFullName(this.firstName, this.middleName, this.lastName);
        setAge(DOB);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setFullName(this.firstName, this.middleName, this.lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setFullName(this.firstName, this.middleName, this.lastName);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String midleName) {
        this.middleName = midleName;
        setFullName(this.firstName, this.middleName, this.lastName);
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String firstName, String middleName, String lastName) {
        this.fullName = firstName + (middleName.length() == 0 ? "" : " ") + middleName + (lastName.length() == 0 ? "" : " ") + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
        setAge(DOB);
    }

    public Integer getAge() {
        return age;
    }

    private void setAge(LocalDate DOB) {
        // calculating age
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                ", age=" + age +
                '}';
    }
}
