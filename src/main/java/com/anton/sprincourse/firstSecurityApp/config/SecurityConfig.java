package com.anton.sprincourse.firstSecurityApp.config;

import com.anton.sprincourse.firstSecurityApp.services.HumanDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final HumanDetailsService humanDetailsService;
    @Autowired
    public SecurityConfig(HumanDetailsService humanDetailsService) {
        this.humanDetailsService = humanDetailsService;
    }

    public void config(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(humanDetailsService);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
