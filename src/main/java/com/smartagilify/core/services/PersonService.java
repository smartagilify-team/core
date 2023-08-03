package com.smartagilify.core.services;

import com.smartagilify.core.entities.Person;
import com.smartagilify.core.mappers.person.PersonMapper;
import com.smartagilify.core.model.person.PersonDTO;
import com.smartagilify.core.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseService<Person, PersonRepository, PersonMapper, PersonDTO> {

    public PersonService(PersonMapper mapper, PersonRepository jpaRepository) {
        super(mapper, jpaRepository);
    }
}
