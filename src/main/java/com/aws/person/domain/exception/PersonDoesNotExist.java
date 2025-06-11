package com.aws.person.domain.exception;

public class PersonDoesNotExist extends RuntimeException {
    public PersonDoesNotExist(String message) {
        super(message);
    }
}
