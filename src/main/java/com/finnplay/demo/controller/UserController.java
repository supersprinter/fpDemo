package com.finnplay.demo.controller;

import com.finnplay.demo.dto.UserDTO;
import com.finnplay.demo.model.User;
import com.finnplay.demo.service.SecurityService;
import com.finnplay.demo.service.UserService;
import com.finnplay.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
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
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        redirectAttributes.addFlashAttribute("username", userForm.getUsername());

        return "redirect:/welcome";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("username") String username,
//                               RedirectAttributes redirectAttributes) {
//
//        redirectAttributes.addFlashAttribute("username", username);
//
//        return "redirect:/welcome";
//    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout, @ModelAttribute("userForm") UserDTO userForm) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        model.addAttribute("userForm", new UserDTO());

        return "login";
    }

//    @GetMapping({"/"})
//    public String welcome2(Model model, @ModelAttribute("username") String username) {
//        return "welcome";
//    }
    @PostMapping({"/login-check"})
    public String welcome2(@ModelAttribute("userForm") UserDTO userForm, Model model, BindingResult bindingResult) {
        model.addAttribute("userForm", userService.findByUsername(userForm.getUsername()));
        return "welcome";
    }


    @GetMapping("/welcome")
    public String welcome(Model model, @ModelAttribute("username") String username) {
        model.addAttribute("userForm", userService.findByUsername(username));
        return "welcome";
    }

    @PostMapping("/welcome")
    public String welcome(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "welcome";
        }

        userService.save(userForm);

        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "User was updated successfully");
        redirectAttributes.addFlashAttribute("username", userForm.getUsername());

        return "redirect:/welcome";
    }


    @PostMapping("/changePassword")
    public String updateLogin(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        return "welcome";
    }
}
