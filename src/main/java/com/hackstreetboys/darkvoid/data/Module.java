package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Module {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id String moduleId;
    private @NotBlank String moduleName;
    private @ManyToOne Staff coordinator;
    private @NotBlank String topics;
    private @NotNull int numberOfStudents;
    private @NotNull int maxStudents;
    private @NotNull boolean running;

    public Module() {}

    public Module(String moduleId, String moduleName, Staff coordinator, String topics, int numberOfStudents, int maxStudents) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.coordinator = coordinator;
        this.topics = topics;
        this.numberOfStudents = numberOfStudents;
        this.maxStudents = maxStudents;
        this.running = true;
    }
}
