package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.dto.person.GetPersonDto;
import com.youcode.wdcmanager.dto.person.UpdatePersonDto;
import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<GetPersonDto> getAll() {
        return personService.findAll().stream()
                .map((p) -> modelMapper.map(p, GetPersonDto.class)).toList();
    }

    @PostMapping
    public GetPersonDto create(@RequestBody GetPersonDto personDto) {
        Person person = modelMapper.map(personDto, Person.class);
        Person createdPerson = personService.create(person);
        return modelMapper.map(createdPerson, GetPersonDto.class);
    }

    @PatchMapping("/{id}")
    public GetPersonDto update(@PathVariable Long id, @RequestBody UpdatePersonDto personDto) {
        Person person = modelMapper.map(personDto, Person.class);
        Person updatedPerson = personService.update(person);
        return modelMapper.map(updatedPerson, GetPersonDto.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.deleteById(id);
    }

    @GetMapping("/{id}")
    public GetPersonDto getOne(@PathVariable Long id) {
        Person person = personService.findById(id);
        return modelMapper.map(person, GetPersonDto.class);
    }


}
