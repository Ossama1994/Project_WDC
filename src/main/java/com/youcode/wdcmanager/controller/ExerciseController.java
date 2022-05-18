package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.dto.exercise.ExerciseDto;
import com.youcode.wdcmanager.entity.Exercise;
import com.youcode.wdcmanager.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ExerciseDto> getAll() {
        return exerciseService.findAll().stream()
                .map((p) -> modelMapper.map(p, ExerciseDto.class)).toList();
    }

    @PostMapping
    public ExerciseDto create(@RequestBody ExerciseDto exerciseDto) {
        Exercise exercise = modelMapper.map(exerciseDto, Exercise.class);
        Exercise createdExercise = exerciseService.create(exercise);
        return modelMapper.map(createdExercise, ExerciseDto.class);
    }

    @PatchMapping("/{id}")
    public ExerciseDto update(@PathVariable Long id, @RequestBody Exercise exercise) {
        Exercise updatedExercise = exerciseService.update(exercise);
        return modelMapper.map(updatedExercise, ExerciseDto.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exerciseService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ExerciseDto getOne(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        return modelMapper.map(exercise, ExerciseDto.class);
    }
}
