package com.finnplay.demo.controller;

import com.finnplay.demo.model.dto.UserDTO;
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
import java.util.UUID;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/welcome")
    public String welcome(Model model, HttpSession httpSession) {

        UserDTO user = userService.findById((UUID)httpSession.getAttribute("id"));

        model.addAttribute("userForm", user);

        return "welcome";
    }


    @PostMapping("/welcome")
    public String welcome(@ModelAttribute("userForm") UserDTO userForm, BindingResult result,
                               RedirectAttributes redirectAttributes) {
        userValidator.validate(userForm, result);

        if (result.hasErrors()) {
            return "welcome";
        }

        userService.update(userForm);

        redirectAttributes.addFlashAttribute("success", "User was updated successfully");

        return "redirect:/welcome";
    }

}
