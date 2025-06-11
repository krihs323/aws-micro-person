package com.aws.person.domain.exception;

public enum ExceptionValidationsResponse {
    PERSON_VALIATION_NAME("El nombre no debe ser nulo"),
    PERSON_VALIDATION_LAST_NAME("El apellido no debe ser nulo"),
    PERSON_VALIDATION_NUMBER_ID("El documento debe ser númerico"),
    PERSON_VALIDATION_TELEPHONE("El teléfono debe ser valido"),
    PERSON_VALIDATION_EMAIL("El email es incorrecto"),
    PERSON_VALIATION_AGE("El usuario no debe ser menor de edad"),;

    private String message;

    ExceptionValidationsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}