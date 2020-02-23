package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Module {
    private @Id @GeneratedValue String moduleCode;
    private @NotBlank String moduleName;
    private @OneToOne Staff coordinator;
    private @NotBlank String topics;

    public Module() {}

    public Module(String moduleName, Staff coordinator, String topics) {
        this.moduleName = moduleName;
        this.coordinator = coordinator;
        this.topics = topics;
    }
}
