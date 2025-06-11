package com.aws.person.application.handler;

import com.aws.person.application.dto.PersonRequest;
import com.aws.person.application.dto.PersonResponse;

public interface IPersonHandler {

    void savePerson(PersonRequest personRequest);
    PersonResponse getPersonById(Long id);
}