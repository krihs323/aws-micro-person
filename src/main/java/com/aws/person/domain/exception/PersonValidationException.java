package com.aws.person.domain.exception;

public class PersonValidationException extends RuntimeException {

    public PersonValidationException(String message) {
        super(message);
    }

}
