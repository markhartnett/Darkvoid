package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class ModuleEnrolment {
    private @Id @GeneratedValue int enrolmentId;
    private @OneToOne Module module;
    private @OneToOne Student student;
    private @NotBlank String grade;

    public ModuleEnrolment(){}

    public ModuleEnrolment(Module module, Student student, String grade) {
        this.module = module;
        this.student = student;
        this.grade = grade;
    }
}
