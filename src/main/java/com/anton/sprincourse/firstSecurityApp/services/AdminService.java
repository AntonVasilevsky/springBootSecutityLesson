package com.anton.sprincourse.firstSecurityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasRole('ROLE_ADMIN')") //@PreAuthorize("hasRole('ROLE_ADMIN') or/and hasRole(..)")
    public void doAdminStuff(){
        System.out.println("Only admin here");
    }
}
