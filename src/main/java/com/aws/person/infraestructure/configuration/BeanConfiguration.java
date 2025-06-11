package com.aws.person.infraestructure.configuration;

import com.aws.person.domain.api.IPersonServicePort;
import com.aws.person.domain.spi.IPersonPersistencePort;
import com.aws.person.domain.usercase.PersonUserCase;
import com.aws.person.infraestructure.output.jpa.adapter.PersonJpaAdapter;
import com.aws.person.infraestructure.output.jpa.mapper.PersonEntityMapper;
import com.aws.person.infraestructure.output.jpa.repository.IPersonRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class BeanConfiguration {

    private final IPersonRepository personRepository;
    private final PersonEntityMapper personEntityMapper;

    public BeanConfiguration(IPersonRepository personRepository, PersonEntityMapper personEntityMapper) {
        this.personRepository = personRepository;
        this.personEntityMapper = personEntityMapper;
    }

    @Bean
    public IPersonPersistencePort personPersistencePort(){
        return new PersonJpaAdapter(personRepository, personEntityMapper);
    }

    @Bean
    public IPersonServicePort userServicePort(){
        return new PersonUserCase(personPersistencePort());
    }

}
