package com.finnplay.demo.controller;

import com.finnplay.demo.model.dto.PasswordDTO;
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
public class ChangePasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("passwordDTO", new PasswordDTO());
        return "changePassword";
    }


    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("passwordDTO") PasswordDTO passwordDTO, BindingResult result,
                               RedirectAttributes redirectAttributes, HttpSession httpSession) {

        UUID userId = (UUID)httpSession.getAttribute("id");

        userValidator.validatePassword(passwordDTO, userId, result);

        if (result.hasErrors()) {
            return "changePassword";
        }

        userService.updatePassword(passwordDTO, userId);

        redirectAttributes.addFlashAttribute("success", "User password was updated successfully");

        return "redirect:/welcome";
    }
}
