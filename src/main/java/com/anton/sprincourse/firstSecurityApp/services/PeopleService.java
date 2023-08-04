package com.anton.sprincourse.firstSecurityApp.services;

import com.anton.sprincourse.firstSecurityApp.models.Human;
import com.anton.sprincourse.firstSecurityApp.repositories.HumanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {
    private final HumanRepository humanRepository;

    public PeopleService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }
    public Optional<Human> getHumanByName(String name) {
        return humanRepository.findByName(name);
    }
}
