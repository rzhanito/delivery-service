package ru.rzhanito.dc.exception;

public class EntityAlreadyExistsException extends Exception{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
