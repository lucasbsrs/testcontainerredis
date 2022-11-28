package com.lucas.testcontainerredis.controllers;

import com.lucas.testcontainerredis.entities.Person;
import com.lucas.testcontainerredis.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Person>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public Person post(@RequestBody Person person) {
        return repository.save(person);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
