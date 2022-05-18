package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.dto.responsible.ResponsibleDto;
import com.youcode.wdcmanager.entity.Responsible;
import com.youcode.wdcmanager.service.ResponsibleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/responsible")
@RequiredArgsConstructor @Secured({"ROLE_ADMIN"})
public class ResponsibleController {
    private final ResponsibleService responsibleService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ResponsibleDto> getAll() {
        return responsibleService.findAll().stream()
                .map((p) -> modelMapper.map(p, ResponsibleDto.class)).toList();
    }

    @PostMapping
    public ResponsibleDto create(@RequestBody ResponsibleDto responsibleDto) {
        Responsible responsible = modelMapper.map(responsibleDto, Responsible.class);
        Responsible createdResponsible = responsibleService.create(responsible);
        return modelMapper.map(createdResponsible, ResponsibleDto.class);
    }

    @PatchMapping("/{id}")
    public ResponsibleDto update(@PathVariable Long id, @RequestBody Responsible responsible) {
        Responsible updatedResponsible = responsibleService.update(responsible);
        return modelMapper.map(updatedResponsible, ResponsibleDto.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        responsibleService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponsibleDto getOne(@PathVariable Long id) {
        Responsible responsible = responsibleService.findById(id);
        return modelMapper.map(responsible, ResponsibleDto.class);
    }
}
