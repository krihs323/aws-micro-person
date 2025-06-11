package com.aws.person.domain.usercase;

import com.aws.person.domain.exception.ExceptionValidationsResponse;
import com.aws.person.domain.exception.PersonCaseValidationException;
import com.aws.person.domain.model.Role;
import com.aws.person.domain.model.Person;
import com.aws.person.domain.spi.IPersonPersistencePort;
import com.aws.person.domain.exception.PersonAlreadyExistException;
import com.aws.person.domain.exception.PersonValidationException;
import com.aws.person.domain.exception.ExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class personUserCaseTest {

    @Mock
    private IPersonPersistencePort personPersistencePort;


    @InjectMocks
    
    private PersonUserCase personUserCase;

    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDate birthDate = LocalDate.of(1989,3,23);
        person = new Person("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cris@hotmail.com", "34567", Role.OWNER, 1L, null);
    }


    @Test
    @DisplayName("Save owner should save")
    void savePerson() {
        doNothing().when(personPersistencePort).save(any());
        personUserCase.savePerson(person);
        verify(personPersistencePort).save(person);
    }

    @Test
    @DisplayName("Save owner should fail with OwnerValidationException")
    void testErrorWhenIsNotAdult(){
        doNothing().when(personPersistencePort).save(any());

        LocalDate birthDate = LocalDate.of(2020,3,23);

        PersonValidationException exception = assertThrows(PersonValidationException.class, () ->
            new Person("Cristian", "Botina", 123456L, "3155828235",
                    birthDate, "cris@hotmail.com", "34567", Role.ADMIN, 1L, null)
        );
        assertEquals(ExceptionValidationsResponse.PERSON_VALIATION_AGE.getMessage(), exception.getMessage());

    }

   

    @Test
    @DisplayName("Get person by email")
    void findByEmail() {

        Mockito.when(personPersistencePort.getPersonById(anyLong())).thenReturn(Optional.of(person));
        PersonAlreadyExistException exception = assertThrows(PersonAlreadyExistException.class, () ->
            personUserCase.getPersonById(anyLong())
        );
        assertEquals(ExceptionResponse.PERSON_VALIDATION_EXIST.getMessage(), exception.getMessage());
    }



    @Test
    @DisplayName("Save person should dont save")
    void savePersonVWhenpersonAlreadyExist() {
        Mockito.when(personPersistencePort.getPersonById(person.getId())).thenReturn(Optional.of(person));

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