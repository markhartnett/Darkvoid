package com.hackstreetboys.darkvoid.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ModuleEnrolment {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int enrolmentId;
    private @ManyToOne Module module;
    private @ManyToOne Student student;
    private String grade;

    public ModuleEnrolment(){}

    public ModuleEnrolment(Module module, Student student, String grade) {
        this.module = module;
        this.student = student;
        this.grade = grade;
    }

    public ModuleEnrolment(Module module, Student student) {
        this.module = module;
        this.student = student;
    }
}
