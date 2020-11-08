package com.finnplay.demo.controller;

import com.finnplay.demo.model.dto.UserDTO;
import com.finnplay.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(Model model, String error) {

        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid");
        }
        return "login";
    }


    @GetMapping(value = "/loginProcess")
    public String loginProcess(Principal principal, HttpSession httpSession) {

        UserDTO userDTO = userService.findByUsername(principal.getName());

        httpSession.setAttribute("id", userDTO.getId());

        return "redirect:/welcome";
    }
}
