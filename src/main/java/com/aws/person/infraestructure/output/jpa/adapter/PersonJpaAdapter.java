package com.aws.person.infraestructure.output.jpa.adapter;

import com.aws.person.domain.model.Person;
import com.aws.person.domain.spi.IPersonPersistencePort;
import com.aws.person.infraestructure.output.jpa.entity.PersonEntity;
import com.aws.person.infraestructure.output.jpa.mapper.PersonEntityMapper;
import com.aws.person.infraestructure.output.jpa.repository.IPersonRepository;

import java.util.Optional;

public class PersonJpaAdapter implements IPersonPersistencePort {

    private final IPersonRepository userRepository;
    private final PersonEntityMapper personEntityMapper;


    public PersonJpaAdapter(IPersonRepository userRepository, PersonEntityMapper personEntityMapper) {
        this.userRepository = userRepository;
        this.personEntityMapper = personEntityMapper;

    }

    @Override
    public void save(Person user) {
        userRepository.save(personEntityMapper.toEntity(user));
    }

    @Override
    public Optional<Person> getPersonById(Long id) {
        Optional<PersonEntity> userFound = userRepository.findById(id);
        return userFound.map(personEntityMapper::toUser);
    }

    @Override
    public Optional<Person> getPersonByEmail(String email) {
        Optional<PersonEntity> userFound = userRepository.findByEmail(email);
        return userFound.map(personEntityMapper::toUser);
    }


}
