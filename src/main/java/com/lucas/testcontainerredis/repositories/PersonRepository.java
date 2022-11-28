package com.lucas.testcontainerredis.repositories;

import com.lucas.testcontainerredis.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
