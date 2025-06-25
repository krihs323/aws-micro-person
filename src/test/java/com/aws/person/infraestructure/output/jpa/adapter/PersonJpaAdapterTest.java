package com.aws.person.infraestructure.output.jpa.adapter;

import com.aws.person.domain.model.Person;
import com.aws.person.infraestructure.output.jpa.entity.PersonEntity;
import com.aws.person.infraestructure.output.jpa.mapper.PersonEntityMapper;
import com.aws.person.infraestructure.output.jpa.repository.IPersonRepository;
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

class PersonJpaAdapterTest {

    @Mock
    private IPersonRepository ownerRepository;

    @Mock
    private PersonEntityMapper personEntityMapper;


    @InjectMocks
    private PersonJpaAdapter userJpaAdapter;

    private Person user;

    private PersonEntity personEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new Person();
        user.setName("Cristian");
        user.setNumberId(123456L);

        user.setEmail("cris@hotmail.com");
            personEntity = new PersonEntity();
        personEntity.setId(1L);
        personEntity.setEmail("cris@hotmail.com");
        personEntity.setName("cristian");

    }

    @Test
    @DisplayName("Save owner")
    void saveOwner() {
        when(ownerRepository.save(any())).thenReturn(any());

        userJpaAdapter.save(user);

        verify(ownerRepository, never()).save(personEntity);

    }

    @Test
    @DisplayName("Get rol from Owner")
    void getRolFromOwner(){

        Optional<PersonEntity> ownerMock = Optional.of(personEntity);

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(ownerMock);
        Mockito.when(personEntityMapper.toUser(any())).thenReturn(user);
        Optional<Person> userExpected = userJpaAdapter.getPersonById(anyLong());

        assertEquals(userExpected.get().getEmail(), user.getEmail());
    }

    @Test
    @DisplayName("Get rol from Owner return exception not exist")
    void getRolFromOwnerException(){

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Person> userResponse =  userJpaAdapter.getPersonById(anyLong());

        assertTrue(userResponse.isEmpty());

    }



}