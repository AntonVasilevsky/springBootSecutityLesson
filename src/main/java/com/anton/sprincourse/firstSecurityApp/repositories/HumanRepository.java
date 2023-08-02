package com.anton.sprincourse.firstSecurityApp.repositories;

import com.anton.sprincourse.firstSecurityApp.models.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumanRepository extends JpaRepository<Human, Integer> {
    Optional<Human> findByName(String name);
}
