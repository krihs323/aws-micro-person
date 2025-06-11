package com.aws.person.domain.exception;

public enum ExceptionResponse {
    PERSON_VALIDATION_EXIST("La persona ya fue creada con el correo: "),
    PERSON_VALIDATION_NOT_FOUND("La persona que intenta buscar no existe");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}