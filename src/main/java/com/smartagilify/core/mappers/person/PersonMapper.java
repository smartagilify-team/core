package com.smartagilify.core.mappers.person;

import com.smartagilify.core.entities.Person;
import com.smartagilify.core.mappers.BaseMapper;
import com.smartagilify.core.model.person.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<Person, PersonDTO> {
}
