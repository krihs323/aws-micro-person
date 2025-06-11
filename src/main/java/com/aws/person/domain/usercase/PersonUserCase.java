package com.aws.person.domain.usercase;

import com.aws.person.domain.api.IPersonServicePort;
import com.aws.person.domain.model.Person;
import com.aws.person.domain.spi.IPersonPersistencePort;
import com.aws.person.domain.validations.PersonValidations;
import com.aws.person.domain.exception.PersonAlreadyExistException;
import com.aws.person.domain.exception.ExceptionResponse;
import java.util.Optional;

public class PersonUserCase implements IPersonServicePort {

    private final IPersonPersistencePort personPersistencePort;

    public PersonUserCase(IPersonPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public void savePerson(Person person) {
        PersonValidations.savePerson(person);
        if (personPersistencePort.getPersonByEmail(person.getEmail()).isPresent()) {
            throw new PersonAlreadyExistException(ExceptionResponse.PERSON_VALIDATION_EXIST.getMessage() + person.getEmail());
        }
        personPersistencePort.save(person);
    }


    @Override
    public Person getPersonById(Long id) {
        Optional<Person> personFoundByEmail = personPersistencePort.getPersonById(id);
        if (personFoundByEmail.isEmpty()) {
            throw new PersonAlreadyExistException(ExceptionResponse.PERSON_VALIDATION_NOT_FOUND.getMessage());
        }
        return personFoundByEmail.get();
    }



}
