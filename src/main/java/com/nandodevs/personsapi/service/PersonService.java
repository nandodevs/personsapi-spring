package com.nandodevs.personsapi.service;

import com.nandodevs.personsapi.dto.MessageResponseDTO;
import com.nandodevs.personsapi.entity.Person;
import com.nandodevs.personsapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId()) //Messagem ao salvar dados
                .build();
    }
}
