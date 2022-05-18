package com.youcode.wdcmanager.repository;

import com.youcode.wdcmanager.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
