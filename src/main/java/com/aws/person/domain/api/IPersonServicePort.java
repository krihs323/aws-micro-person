package com.aws.person.domain.api;

import com.aws.person.domain.model.Person;

public interface IPersonServicePort {

    void savePerson(Person person);

    Person getPersonById(Long id);

}
