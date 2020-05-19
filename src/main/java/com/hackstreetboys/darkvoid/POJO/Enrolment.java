package com.hackstreetboys.darkvoid.POJO;

public class Enrolment {
    private int studentId;

    private String moduleId;

    public Enrolment(int studentId, String moduleId) {
        this.studentId = studentId;
        this.moduleId = moduleId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getModuleId() {
        return moduleId;
    }
}
