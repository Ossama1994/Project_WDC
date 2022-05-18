package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Participant;
import com.youcode.wdcmanager.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public Participant create(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant update(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant findById(Long id) {
        return participantRepository.findById(id).orElse(null);
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }
}
