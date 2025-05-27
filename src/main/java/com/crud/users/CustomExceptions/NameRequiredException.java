package com.crud.users.CustomExceptions;

public class NameRequiredException extends Exception{
    public NameRequiredException(String message) {
        super(message);
    }
}
