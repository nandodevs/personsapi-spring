package com.nandodevs.personsapi.service;


import com.nandodevs.personsapi.PersonService;
import com.nandodevs.personsapi.dto.request.PersonDTO;
import com.nandodevs.personsapi.dto.response.MessageResponseDTO;
import com.nandodevs.personsapi.entity.Person;
import com.nandodevs.personsapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static com.nandodevs.personsapi.utils.PersonUtils.createFakeDTO;
import static com.nandodevs.personsapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO exepectedSuccesMessage = createdExpectedMessageResponse(expectedSavedPerson.getId());

        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        assertEquals(exepectedSuccesMessage, succesMessage);
    }

    private MessageResponseDTO createdExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
