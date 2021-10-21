package com.nandodevs.personsapi.mapper;

import com.nandodevs.personsapi.dto.request.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.nandodevs.personsapi.entity.Person;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy") //Mapear data de nascimento
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
