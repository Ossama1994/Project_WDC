package com.youcode.wdcmanager.repository;

import com.youcode.wdcmanager.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
