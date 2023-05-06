package com.smartagilify.core.services;

import com.smartagilify.core.entities.Person;
import com.smartagilify.core.mappers.person.PersonMapper;
import com.smartagilify.core.model.person.PersonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseService<Person, PersonMapper, PersonDTO> {

    protected PersonService(JpaRepository<Person, Long> jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected Class<PersonMapper> getMapper() {
        return PersonMapper.class;
    }
}
