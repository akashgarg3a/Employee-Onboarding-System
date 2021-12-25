package com.example.demo.Onboarding;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

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
    @Column
    @NotNull
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String middleName;
    @Transient
    private String fullName;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, String middleName, String email, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.dob = dob;
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
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        setFullName("");
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = this.firstName + (this.middleName.length() == 0 ? "" : " ") + this.middleName + (this.lastName.length() == 0 ? "" : " ") + this.lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        setAge(0);
        return age;
    }

    public void setAge(Integer age) {
        this.age = Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
