package com.aws.person.domain.api;

import com.aws.person.application.dto.AuthenticationRequest;

public interface IAuthenticationServicePort {

    String authenticate(AuthenticationRequest request);
}
