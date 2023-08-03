package com.anton.sprincourse.firstSecurityApp.controllers;

import com.anton.sprincourse.firstSecurityApp.services.HumanDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final HumanDetailsService humanDetailsService;

    public AuthController(HumanDetailsService humanDetailsService) {
        this.humanDetailsService = humanDetailsService;
    }

    @GetMapping("/login")
    public String getLogin(){
        return "auth/login";
    }
    @PostMapping("/process_login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        UserDetails userDetails = humanDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getUsername() + " " + userDetails.getPassword()); // ??
        if(userDetails.getUsername().equals(username) && userDetails.getPassword().equals(password)) {
            return "redirect:/hello.html";
        }
        return "auth/login?error";
    }

}
