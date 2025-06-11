package com.aws.person.application.mapper;

import com.aws.person.application.dto.PersonResponse;
import com.aws.person.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolResponseMapper {

    PersonResponse toResponse(Person person);
}
