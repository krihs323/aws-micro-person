package com.aws.person.application.mapper;

import com.aws.person.application.dto.PersonRequest;
import com.aws.person.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PersonRequestMapper {

    Person toUser(PersonRequest personRequest);

}
