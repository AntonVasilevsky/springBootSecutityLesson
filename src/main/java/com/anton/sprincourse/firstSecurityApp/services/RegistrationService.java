package com.anton.sprincourse.firstSecurityApp.services;

import com.anton.sprincourse.firstSecurityApp.models.Human;
import com.anton.sprincourse.firstSecurityApp.repositories.HumanRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final HumanRepository humanRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(HumanRepository humanRepository, PasswordEncoder passwordEncoder) {
        this.humanRepository = humanRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(Human human) {
        human.setPassword(passwordEncoder.encode(human.getPassword()));
        human.setRole("ROLE_USER");
        humanRepository.save(human);
    }
}
