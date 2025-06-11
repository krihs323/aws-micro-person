package com.aws.person.application.dto;

import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponse {

    private String name;

    private Long numberId;

    private String email;

}
