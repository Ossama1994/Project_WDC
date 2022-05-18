package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Administrator;
import com.youcode.wdcmanager.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    public Administrator create(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public Administrator update(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public Administrator findById(Long id) {
        return administratorRepository.findById(id).orElse(null);
    }

    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    public void deleteById(Long id) {
        administratorRepository.deleteById(id);
    }
}
