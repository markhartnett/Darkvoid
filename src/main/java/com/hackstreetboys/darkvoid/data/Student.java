package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Student {
    private @Id @GeneratedValue Integer studentId;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotBlank String address;
    private @NotBlank String phoneNumber;
    private @NotBlank String email;


    public Student() {}

    public Student(Integer studentId, String firstName, String lastName, String address, String phoneNumber, String email){
        super();
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getStudentId() { return studentId; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
}
