package com.hackstreetboys.darkvoid.exception;

public class StaffNotFoundException extends Exception {
    private Integer staffId;

    public StaffNotFoundException(Integer staffId){
        super(String.format("Cannot find staff member with ID: %d", staffId));
    }
}
