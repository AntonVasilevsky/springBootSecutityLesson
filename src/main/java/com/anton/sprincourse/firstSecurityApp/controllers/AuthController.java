package com.anton.sprincourse.firstSecurityApp.controllers;

import com.anton.sprincourse.firstSecurityApp.models.Human;
import com.anton.sprincourse.firstSecurityApp.services.HumanDetailsService;
import com.anton.sprincourse.firstSecurityApp.services.RegistrationService;
import com.anton.sprincourse.firstSecurityApp.util.HumanValidator;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final HumanDetailsService humanDetailsService;
    private final HumanValidator humanValidator;
    private final RegistrationService registrationService;

    public AuthController(HumanDetailsService humanDetailsService, HumanValidator humanValidator, RegistrationService registrationService) {
        this.humanDetailsService = humanDetailsService;
        this.humanValidator = humanValidator;
        this.registrationService = registrationService;
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
    @GetMapping("/registration")
    public String registration(@ModelAttribute("human")Human human) {
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("human") @Valid Human human, BindingResult bindingresult) {
        humanValidator.validate(human, bindingresult);
        registrationService.register(human);
        if(bindingresult.hasErrors()){
            return "auth/registration";
        }

        return "redirect:/auth/login";
    }
    @GetMapping("/login_fail")
        public String loginFail(){
            return "auth/login_fail";
        }

}


