package com.nandodevs.personsapi.service;

import com.nandodevs.personsapi.dto.request.PersonDTO;
import com.nandodevs.personsapi.dto.response.MessageResponseDTO;
import com.nandodevs.personsapi.entity.Person;
import com.nandodevs.personsapi.mapper.PersonMapper;
import com.nandodevs.personsapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId()) //Messagem ao salvar dados
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream() //Api de Streams do Java
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
