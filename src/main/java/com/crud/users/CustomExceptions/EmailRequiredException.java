package com.crud.users.CustomExceptions;

public class EmailRequiredException extends Exception{
    public EmailRequiredException(String message) {
        super(message);
    }
}
