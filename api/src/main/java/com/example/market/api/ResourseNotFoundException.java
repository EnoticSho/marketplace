package com.example.market.api;

public class ResourseNotFoundException extends RuntimeException{
    public ResourseNotFoundException(String message) {
        super(message);
    }
}
