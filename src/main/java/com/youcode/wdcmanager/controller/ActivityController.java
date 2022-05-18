package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.dto.activity.CreateActivityDto;
import com.youcode.wdcmanager.dto.activity.UpdateActivityDto;
import com.youcode.wdcmanager.entity.Activity;
import com.youcode.wdcmanager.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/activity")
@RequiredArgsConstructor @Secured({"ROLE_ADMIN"})
public class ActivityController {
    private final ActivityService activityService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CreateActivityDto> getAll() {
        return activityService.findAll().stream()
                .map((p) -> modelMapper.map(p, CreateActivityDto.class)).toList();
    }

    @PostMapping
    public Activity create(@RequestBody CreateActivityDto activityDto) {
        Activity activity = modelMapper.map(activityDto, Activity.class);
        return activityService.create(activity);
    }

    @PatchMapping("/{id}")
    public Activity update(@PathVariable Long id, @RequestBody UpdateActivityDto activityDto) {
        Activity activity = modelMapper.map(activityDto, Activity.class);
        return activityService.update(activity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        activityService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Activity getOne(@PathVariable Long id) {
        return activityService.findById(id);
    }


}
