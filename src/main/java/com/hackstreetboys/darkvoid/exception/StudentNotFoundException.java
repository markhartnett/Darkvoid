package com.hackstreetboys.darkvoid.exception;

public class StudentNotFoundException extends Exception {
    private Integer studentId;

    public StudentNotFoundException(Integer studentId){
        super(String.format("Cannot find student with ID: %d", studentId));
    }
}
