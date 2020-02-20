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
}
