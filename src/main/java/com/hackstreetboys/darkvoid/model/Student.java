package com.hackstreetboys.darkvoid.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Student {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer studentId;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank String phoneNumber;
    private @NotBlank String email;
    private @NotBlank String gender;
    private @NotBlank String nationality;
    private @NotNull float feesdue;
    private @NotNull float feespaid;


    public Student() {}

    public Student(String firstName, String lastName, String username, String password, String phoneNumber, String email, String gender, String nationality, float feesdue, float feespaid) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.nationality = nationality;
        this.feesdue = feesdue;
        this.feespaid = feespaid;
    }

    public int getID() {
        return studentId;
    }
}
