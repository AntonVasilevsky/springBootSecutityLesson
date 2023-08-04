package com.anton.sprincourse.firstSecurityApp.services;

import com.anton.sprincourse.firstSecurityApp.models.Human;
import com.anton.sprincourse.firstSecurityApp.repositories.HumanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final HumanRepository humanRepository;

    public RegistrationService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }
    @Transactional
    public void register(Human human) {
        humanRepository.save(human);
    }
}
