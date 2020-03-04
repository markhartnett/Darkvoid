package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Staff {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer staffId;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotBlank String gender;
    private @NotBlank String nationality;
    private @NotBlank String username;
    private @NotBlank String password;

    public Staff() {}

    public Staff(String firstName, String lastName, String gender, String nationality, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.username = username;
        this.password = password;
    }
}
