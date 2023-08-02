package com.anton.sprincourse.firstSecurityApp.security;

import com.anton.sprincourse.firstSecurityApp.services.HumanDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final HumanDetailsService humanDetailsService;
    @Autowired
    public AuthProviderImpl(HumanDetailsService humanDetailsService) {
        this.humanDetailsService = humanDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();

        UserDetails humanDetails = humanDetailsService.loadUserByUsername(name); //principal

        String password = authentication.getCredentials().toString();   //credential

        if(!password.equals(humanDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password");

        return new UsernamePasswordAuthenticationToken(humanDetails, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
