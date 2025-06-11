package com.aws.person.infraestructure.output.jpa.mapper;

import com.aws.person.domain.model.Person;
import com.aws.person.infraestructure.output.jpa.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PersonEntityMapper {

    PersonEntity toEntity(Person person);

    Person toUser(PersonEntity personEntity);

}
