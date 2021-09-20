package com.example.parking.validator;

import com.example.parking.dao.PersonnelRepository;
import com.example.parking.dao.UserRepository;
import com.example.parking.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonnelValidator implements Validator {
    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Personnel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Personnel personnel = (Personnel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (personnel.getUsername().length() < 6 || personnel.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (personnelRepository.findByUsername(personnel.getUsername()) != null || userRepository.findByUsername(personnel.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (personnel.getPassword().length() < 8 || personnel.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!personnel.getPasswordConfirm().equals(personnel.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        // à ajouter d'autre validation
    }
}