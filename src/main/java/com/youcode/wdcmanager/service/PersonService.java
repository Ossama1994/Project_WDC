package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public Person create(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public List<Person> createAll(Iterable<Person> person){
        return personRepository.saveAll(person);
    }

    public Person update(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public Person updateWithoutPasswordEncoder(Person person) {
        return personRepository.save(person);
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person findById(String id) {
        return personRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

}
