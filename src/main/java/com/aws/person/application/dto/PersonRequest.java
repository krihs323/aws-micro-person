package com.aws.person.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonRequest {

        private String name;

        @Digits(integer = 15, fraction = 0, message = "El número de identidad debe ser númerico")
        private Long numberId;

        private String email;
}
