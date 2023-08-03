package com.smartagilify.core.controllers;

import com.smartagilify.core.constant.RestAddress;
import com.smartagilify.core.entities.Person;
import com.smartagilify.core.mappers.person.PersonMapper;
import com.smartagilify.core.model.person.PersonDTO;
import com.smartagilify.core.repositories.PersonRepository;
import com.smartagilify.core.services.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestAddress.PERSON)
public class PersonController extends BaseController<Person, PersonRepository, PersonService, PersonMapper, PersonDTO> {

    public PersonController(PersonService service) {
        super(service);
    }

}
