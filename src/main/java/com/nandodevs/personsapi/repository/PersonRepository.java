package com.nandodevs.personsapi.repository;

import com.nandodevs.personsapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
