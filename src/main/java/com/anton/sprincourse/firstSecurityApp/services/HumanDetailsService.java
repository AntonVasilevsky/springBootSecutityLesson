package com.anton.sprincourse.firstSecurityApp.services;

import com.anton.sprincourse.firstSecurityApp.models.Human;
import com.anton.sprincourse.firstSecurityApp.repositories.HumanRepository;
import com.anton.sprincourse.firstSecurityApp.security.HumanDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumanDetailsService implements UserDetailsService {
    private final HumanRepository humanRepository;

    public HumanDetailsService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Human> human = humanRepository.findByName(username);
       if(human.isEmpty()){
           throw new UsernameNotFoundException("User not found");
       }
       return new HumanDetails(human.get());
    }
}
