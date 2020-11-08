package com.finnplay.demo.validator;

import com.finnplay.demo.model.dto.PasswordDTO;
import com.finnplay.demo.model.dto.UserDTO;
import com.finnplay.demo.model.entity.User;
import com.finnplay.demo.service.UserService;
import com.finnplay.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.UUID;


@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
        if (userDTO.getUsername().length() < 4 || userDTO.getUsername().length() > 32) {
            errors.rejectValue("username", "username.invalid.length");
        }

        if(userDTO.getId() == null ) { // new user
            if (userService.findByUsername(userDTO.getUsername()) != null) {
                errors.rejectValue("username", "username.duplicate");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
            if (userDTO.getPassword().length() < 6 || userDTO.getPassword().length() > 32) {
                errors.rejectValue("password", "password.invalid.length");
            }

            if (!userDTO.getPasswordConfirm().equals(userDTO.getPassword())) {
                errors.rejectValue("passwordConfirm", "passwordConfirm.dont.match");
            }
        }

        if (!userDTO.getEmail().isEmpty() && !Utils.isEmailValid(userDTO.getEmail())) {
            errors.rejectValue("email", "email.invalid.format");
        }

        if (!userDTO.getBirthday().isEmpty() && !Utils.isDateValid(userDTO.getBirthday())) {
            errors.rejectValue("birthday", "birthday.invalid.format");
        }
    }


    public void validatePassword(PasswordDTO passwordDTO, UUID userId, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "field.required");

        UserDTO userDTO = userService.findById(userId);

        if(!bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(),userDTO.getPassword())) {
            errors.rejectValue("oldPassword", "password.invalid");
        }

        if (passwordDTO.getNewPassword().length() < 1 || passwordDTO.getNewPassword().length() > 32) {
            errors.rejectValue("newPassword", "password.invalid.length");
        }

        if (!passwordDTO.getPasswordConfirm().equals(passwordDTO.getNewPassword())) {
            errors.rejectValue("passwordConfirm", "passwordConfirm.dont.match");
        }
    }

}
