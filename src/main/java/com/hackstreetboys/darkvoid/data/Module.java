package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Module {
    private @Id @GeneratedValue String moduleCode;
    private @NotBlank String moduleName;
    private @OneToOne Staff coordinator;
    private @OneToMany List<Student> students;

    public Module() {}
}
