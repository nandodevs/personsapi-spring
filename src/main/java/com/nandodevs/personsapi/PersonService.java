package com.nandodevs.personsapi;

import com.nandodevs.personsapi.dto.request.PersonDTO;
import com.nandodevs.personsapi.dto.response.MessageResponseDTO;
import com.nandodevs.personsapi.entity.Person;
import com.nandodevs.personsapi.exception.PersonNotFoundException;
import com.nandodevs.personsapi.mapper.PersonMapper;
import com.nandodevs.personsapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



//Serviços
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
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream() //Api de Streams do Java
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        //Evita verificação como nulas
        //Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person persontoUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(persontoUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id) //Messagem ao salvar dados
                .build();
    }
}
