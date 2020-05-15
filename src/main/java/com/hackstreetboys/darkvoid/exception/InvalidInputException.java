package com.hackstreetboys.darkvoid.exception;

public class InvalidInputException extends Exception{
    public InvalidInputException(){
        super("Invalid input data was found in the request");
    }
}
