package com.example.esdProject_2.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message)  {
        super(message);
    }
}
