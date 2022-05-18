package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Activity;
import com.youcode.wdcmanager.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public Activity create(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity update(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }


}
