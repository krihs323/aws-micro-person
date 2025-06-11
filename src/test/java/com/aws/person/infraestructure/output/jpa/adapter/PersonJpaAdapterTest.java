package com.aws.person.infraestructure.output.jpa.adapter;

import com.aws.person.domain.model.Person;
import com.aws.person.domain.model.Role;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonJpaAdapterTest {

    @Mock
    private IPersonRepository ownerRepository;

    @Mock
    private PersonEntityMapper personEntityMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PersonJpaAdapter userJpaAdapter;

    private Person user;

    private PersonEntity personEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDate birthDate = LocalDate.of(1989,3,23);

        user = new Person();
        user.setName("Cristian");
        user.setLastName("Botina");
        user.setNumberId(123456L);
        user.setPhoneNumber("3155828235");
        user.setBirthDate(birthDate);
        user.setEmail("cris@hotmail.com");
        user.setPassword("34567");
        user.setRole(Role.OWNER);

        personEntity = new PersonEntity();
        personEntity.setId(1L);
        personEntity.setEmail("cris@hotmail.com");
        personEntity.setName("cristian");
        personEntity.setLastName("botina");
        personEntity.setPassword("123456");
        personEntity.setRole(Role.ADMIN);
        personEntity.setBirthDate(birthDate);
        personEntity.setPhoneNumber("+523155465");
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