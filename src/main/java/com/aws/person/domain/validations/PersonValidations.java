package com.aws.person.domain.validations;

import com.aws.person.domain.exception.ExceptionValidationsResponse;
import com.aws.person.domain.exception.PersonCaseValidationException;
import com.aws.person.domain.model.Person;

public class PersonValidations {

    public static void savePerson(Person person){

        if (person.getName() == null || person.getName().isEmpty()) {
            throw new PersonCaseValidationException(ExceptionValidationsResponse.PERSON_VALIATION_NAME.getMessage());
        }

        if (!validate(person.getNumberId(), ConstantValidation.PATTERN_NUMBER.getValue())) {
            throw new PersonCaseValidationException(ExceptionValidationsResponse.PERSON_VALIDATION_NUMBER_ID.getMessage());
        }

        if (!validate(person.getEmail(), ConstantValidation.PATTERN_EMAIL.getValue())) {
            throw new PersonCaseValidationException(ExceptionValidationsResponse.PERSON_VALIDATION_EMAIL.getMessage());
        }

    }

    private static boolean validate(Object value, String pattern){
        String valueString = "";
        if (value == null) {
            return false;
        }

        if (value instanceof Long) {
            valueString = String.valueOf(value);
        } else if (value instanceof String) {
            valueString = ((String) value).trim();
        }

        return valueString.matches(pattern);
    }
}
