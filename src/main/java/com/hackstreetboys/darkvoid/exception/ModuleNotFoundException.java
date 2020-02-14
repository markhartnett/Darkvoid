package com.hackstreetboys.darkvoid.exception;

public class ModuleNotFoundException extends Exception {
    private String moduleCode;

    public ModuleNotFoundException(String moduleCode){
        super(String.format("Cannot find module with code: %s", moduleCode));
    }
}
