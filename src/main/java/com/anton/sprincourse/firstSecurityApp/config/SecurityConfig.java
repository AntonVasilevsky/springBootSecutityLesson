package com.anton.sprincourse.firstSecurityApp.config;

import com.anton.sprincourse.firstSecurityApp.services.HumanDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final HumanDetailsService humanDetailsService;

    @Autowired
    public SecurityConfig(HumanDetailsService humanDetailsService) {
        this.humanDetailsService = humanDetailsService;
    }

    public void config(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(humanDetailsService).passwordEncoder(getPasswordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                .authorizeHttpRequests(auth -> {
                   auth

                           .requestMatchers("/auth/login", "/auth/registration", "/auth/login_fail")
                           .permitAll()
                           .anyRequest().hasAnyRole("USER", "ADMIN");





                })
                .formLogin(f -> f.loginPage("/auth/login")
                        .loginProcessingUrl("/auth/process_login")
                        .defaultSuccessUrl("/hello", true)
                        .failureUrl("/auth/login_fail")
                        )
                .logout(logout ->
                    logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/auth/login")
                )
                .build();


    }
}
