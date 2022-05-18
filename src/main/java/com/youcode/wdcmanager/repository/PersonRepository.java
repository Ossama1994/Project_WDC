package com.youcode.wdcmanager.repository;

import com.youcode.wdcmanager.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsernameOrEmail(String username, String email);
    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);
}
