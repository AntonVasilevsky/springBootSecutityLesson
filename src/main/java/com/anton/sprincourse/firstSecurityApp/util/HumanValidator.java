package com.anton.sprincourse.firstSecurityApp.util;

import com.anton.sprincourse.firstSecurityApp.models.Human;
import com.anton.sprincourse.firstSecurityApp.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class HumanValidator implements Validator {
    private final PeopleService peopleService;

    public HumanValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Human.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Human human = (Human) target;
       if(peopleService.getHumanByName(human.getName()).isPresent()){
           errors.rejectValue("username", "", "Username already exists");
       }
    }
}
