package com.example.demo.Onboarding;
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
    private String firstname, lastname, middlename;
    @Transient
    private String fullname;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(String firstname, String lastname, String middlename, String email, LocalDate dob) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.dob = dob;
        setFullname(this.firstname, this.middlename, this.lastname);
        setAge(dob);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
        setFullname(this.firstname, this.middlename, this.lastname);
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
        setFullname(this.firstname, this.middlename, this.lastname);
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String midleName) {
        this.middlename = midleName;
        setFullname(this.firstname, this.middlename, this.lastname);
    }

    public String getFullname() {
        setFullname(this.firstname, this.middlename, this.lastname);
        return fullname;
    }

    private void setFullname(String firstname, String middlename, String lastname) {
        this.fullname = firstname + (this.middlename.length() == 0 ? "" : " ") + this.middlename + (this.lastname.length() == 0 ? "" : " ") + this.lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getdob() {
        return dob;
    }

    public void setdob(LocalDate dob) {
        this.dob = dob;
        setAge(dob);
    }

    public Integer getAge() {
        setAge(this.dob);
        return this.age;
    }

    private void setAge(LocalDate dob) {
        // calculating age
        int age = Period.between(this.dob, LocalDate.now()).getYears();
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
