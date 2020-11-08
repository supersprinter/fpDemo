package com.finnplay.demo.controller;

import com.finnplay.demo.model.dto.UserDTO;
import com.finnplay.demo.service.SecurityService;
import com.finnplay.demo.service.UserService;
import com.finnplay.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("userForm", new UserDTO());

        return "registration";
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult result,
                               RedirectAttributes redirectAttributes, HttpSession httpSession) {
        userValidator.validate(userForm, result);

        if (result.hasErrors()) {
            return "registration";
        }

        userService.create(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        redirectAttributes.addFlashAttribute("username", userForm.getUsername());

        httpSession.setAttribute("id", userForm.getId());

        return "redirect:/welcome";
    }

}
