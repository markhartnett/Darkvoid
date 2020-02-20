package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Staff {
    private @Id @GeneratedValue Integer staffId;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotBlank String address;
    private @NotBlank String phoneNumber;
    private @NotBlank String email;

    public Staff() {}
}
