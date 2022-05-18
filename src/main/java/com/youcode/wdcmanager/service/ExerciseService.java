package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Exercise;
import com.youcode.wdcmanager.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public Exercise create(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise update(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise findById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public void deleteById(Long id) {
        exerciseRepository.deleteById(id);
    }
}
