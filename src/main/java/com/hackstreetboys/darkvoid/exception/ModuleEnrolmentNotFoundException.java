package com.hackstreetboys.darkvoid.exception;

public class ModuleEnrolmentNotFoundException extends Exception {
    private String moduleCode;

    public ModuleEnrolmentNotFoundException(int enrolmentId){
        super(String.format("Cannot find module enrolment with code: %s", enrolmentId));
    }
}
