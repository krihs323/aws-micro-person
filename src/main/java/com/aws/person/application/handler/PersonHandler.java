package com.aws.person.application.handler;

import com.aws.person.application.dto.PersonRequest;
import com.aws.person.application.dto.PersonResponse;
import com.aws.person.application.mapper.PersonRequestMapper;
import com.aws.person.application.mapper.RolResponseMapper;
import com.aws.person.domain.api.IPersonServicePort;
import com.aws.person.domain.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonHandler implements IPersonHandler {

    private final IPersonServicePort userServicePort;
    private final RolResponseMapper rolResponseMapper;
    private final PersonRequestMapper personRequestMapper;

    public PersonHandler(IPersonServicePort userServicePort, RolResponseMapper rolResponseMapper, PersonRequestMapper personRequestMapper) {
        this.userServicePort = userServicePort;
        this.rolResponseMapper = rolResponseMapper;
        this.personRequestMapper = personRequestMapper;
    }

    @Override
    public void savePerson(PersonRequest personRequest) {
        Person person = personRequestMapper.toUser(personRequest);
        userServicePort.savePerson(person);
    }

    @Override
    public PersonResponse getPersonById(Long id) {
        Person person = userServicePort.getPersonById(id);
        return rolResponseMapper.toResponse(person);
    }

}
