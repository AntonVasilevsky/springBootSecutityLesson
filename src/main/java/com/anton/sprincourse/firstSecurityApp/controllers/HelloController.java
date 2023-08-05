package com.anton.sprincourse.firstSecurityApp.controllers;

import com.anton.sprincourse.firstSecurityApp.security.HumanDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
    @GetMapping("getUserDetails")
    public String getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HumanDetails humanDetails = (HumanDetails) authentication.getPrincipal();
        System.out.println(humanDetails.getHuman());
        return "hello";
    }
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }
}
