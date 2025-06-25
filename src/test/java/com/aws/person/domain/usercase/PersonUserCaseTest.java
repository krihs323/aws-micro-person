package com.aws.person.domain.usercase;

import com.aws.person.domain.exception.ExceptionValidationsResponse;
import com.aws.person.domain.exception.PersonCaseValidationException;
import com.aws.person.domain.model.Person;
import com.aws.person.domain.spi.IPersonPersistencePort;
import com.aws.person.domain.exception.PersonAlreadyExistException;
import com.aws.person.domain.exception.ExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PersonUserCaseTest {

    @Mock
    private IPersonPersistencePort personPersistencePort;


    @InjectMocks
    
    private PersonUserCase personUserCase;

    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        person = new Person("Cristian",  123456L,
                 "cris@hotmail.com", 1L);
    }


    @Test
    @DisplayName("Save owner should save")
    void savePerson() {
        doNothing().when(personPersistencePort).save(any());
        personUserCase.savePerson(person);
        verify(personPersistencePort).save(person);
    }

    @Test
    @DisplayName("Get person by email")
    void findByEmail() {

        Mockito.when(personPersistencePort.getPersonById(anyLong())).thenReturn(Optional.empty());
        PersonAlreadyExistException exception = assertThrows(PersonAlreadyExistException.class, () ->
            personUserCase.getPersonById(anyLong())
        );
        assertEquals(ExceptionResponse.PERSON_VALIDATION_NOT_FOUND.getMessage(), exception.getMessage());
    }



    @Test
    @DisplayName("Save person should dont save")
    void savePersonVWhenpersonAlreadyExist() {
        Mockito.when(personPersistencePort.getPersonByEmail(person.getEmail())).thenReturn(Optional.of(person));

        PersonAlreadyExistException exception = assertThrows(PersonAlreadyExistException.class, () ->
            personUserCase.savePerson(person)
        );
        assertEquals(ExceptionResponse.PERSON_VALIDATION_EXIST.getMessage() + person.getEmail(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid email in Person")
    void savePersonInvalidEmail() {

        person.setEmail("email.@");
        PersonCaseValidationException exception = assertThrows(PersonCaseValidationException.class, () ->
            personUserCase.savePerson(person)
        );
        assertEquals(ExceptionValidationsResponse.PERSON_VALIDATION_EMAIL.getMessage(), exception.getMessage());

        person.setEmail("");
        PersonCaseValidationException exception2 = assertThrows(PersonCaseValidationException.class, () ->
            personUserCase.savePerson(person)
        );
        assertEquals(ExceptionValidationsResponse.PERSON_VALIDATION_EMAIL.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("Should return error when is invalid name in Person")
    void savePersonInvalidName() {

        person.setName("");
        PersonCaseValidationException exception2 = assertThrows(PersonCaseValidationException.class, () ->
                personUserCase.savePerson(person)
        );
        assertEquals(ExceptionValidationsResponse.PERSON_VALIATION_NAME.getMessage(), exception2.getMessage());
    }


    @Test
    @DisplayName("Should return error when is invalid number id in Person")
    void savePersonInvalidNumberId() {

        person.setNumberId(null);
        PersonCaseValidationException exception = assertThrows(PersonCaseValidationException.class, () ->
                personUserCase.savePerson(person)
        );
        assertEquals(ExceptionValidationsResponse.PERSON_VALIDATION_NUMBER_ID.getMessage(), exception.getMessage());
    }



}