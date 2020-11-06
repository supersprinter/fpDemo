package com.finnplay.demo.validator;

import com.finnplay.demo.dto.UserDTO;
import com.finnplay.demo.model.User;
import com.finnplay.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "username.invalid.length");
        }

        if(user.getId() == null ) {
            if (userService.findByUsername(user.getUsername()) != null) {
                errors.rejectValue("username", "username.duplicate");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
            if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "password.invalid.length");
            }

            if (!user.getPasswordConfirm().equals(user.getPassword())) {
                errors.rejectValue("passwordConfirm", "passwordConfirm.dont.match");
            }
        }

        if (!user.getEmail().isEmpty() && !isEmailValid(user.getEmail())) {
            errors.rejectValue("email", "email.invalid.format");
        }

//        if (user.getBirthday() != null && !isBirthdayValid(user.getBirthday())) {
//            errors.rejectValue("email", "email.invalid.format");
//        }
    }

    private boolean isEmailValid(String email){
        return Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
    }

//    private boolean isBirthdayValid(String birthday){
//        return Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", birthday);
//    }
}
