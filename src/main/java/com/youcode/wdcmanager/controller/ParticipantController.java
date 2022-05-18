package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.dto.participant.ParticipantDto;
import com.youcode.wdcmanager.entity.Participant;
import com.youcode.wdcmanager.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/participant")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ParticipantDto> getAll() {
        return participantService.findAll().stream()
                .map((p) -> modelMapper.map(p, ParticipantDto.class)).toList();
    }

    @PostMapping
    public ParticipantDto create(@RequestBody ParticipantDto participantDto) {
        Participant participant = modelMapper.map(participantDto, Participant.class);
        Participant createdParticipant = participantService.create(participant);
        return modelMapper.map(createdParticipant, ParticipantDto.class);
    }

    @PatchMapping("/{id}")
    public ParticipantDto update(@PathVariable Long id, @RequestBody Participant participant) {
        Participant updatedParticipant = participantService.update(participant);
        return modelMapper.map(updatedParticipant, ParticipantDto.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        participantService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ParticipantDto getOne(@PathVariable Long id) {
        Participant participant = participantService.findById(id);
        return modelMapper.map(participant, ParticipantDto.class);
    }
}
