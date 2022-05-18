package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Responsible;
import com.youcode.wdcmanager.repository.ResponsibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsibleService {
    private final ResponsibleRepository responsibleRepository;

    public Responsible create(Responsible responsible) {
        return responsibleRepository.save(responsible);
    }

    public Responsible update(Responsible responsible) {
        return responsibleRepository.save(responsible);
    }

    public Responsible findById(Long id) {
        return responsibleRepository.findById(id).orElse(null);
    }

    public List<Responsible> findAll() {
        return responsibleRepository.findAll();
    }

    public void deleteById(Long id) {
        responsibleRepository.deleteById(id);
    }
}
