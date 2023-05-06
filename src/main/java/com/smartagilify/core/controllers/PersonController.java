package com.smartagilify.core.controllers;

import com.smartagilify.core.entities.Person;
import com.smartagilify.core.mappers.person.PersonMapper;
import com.smartagilify.core.model.person.PersonDTO;
import com.smartagilify.core.services.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestAddress.PERSON)
@ApiOperation(value = "operation on person",
        notes = "You can do operation on person model.",
        response = ResponseEntity.class)
public class PersonController extends BaseController<Person, PersonMapper, PersonDTO> {
    public PersonController(BaseService<Person, PersonMapper, PersonDTO> service) {
        super(service);
    }
}
