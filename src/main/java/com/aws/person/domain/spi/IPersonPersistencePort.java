package com.aws.person.domain.spi;

import com.aws.person.domain.model.Person;

import java.util.Optional;

public interface IPersonPersistencePort {

    void save(Person person);

    Optional<Person> getPersonById(Long id);

    Optional<Person> getPersonByEmail(String email);
}
