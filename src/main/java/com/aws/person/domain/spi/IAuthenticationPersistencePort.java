package com.aws.person.domain.spi;

import com.aws.person.domain.model.Person;

public interface IAuthenticationPersistencePort {

    String authenticate(Person user);

    void autenticate(String email, String password);
}
