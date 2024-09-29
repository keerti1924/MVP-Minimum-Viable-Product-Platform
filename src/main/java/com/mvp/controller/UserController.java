package com.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvp.model.User;
import com.mvp.service.UserService;

@Controller
@SessionAttributes("currentUser")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@SessionAttribute(name = "currentUser", required = false) User currentUser, Model model) {
    	model.addAttribute("currentUser", currentUser); 
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model) {
        // Using email for login
        User existingUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existingUser != null) {
            model.addAttribute("currentUser", existingUser);
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(@SessionAttribute(name = "currentUser", required = false) User currentUser, Model model) {
    	model.addAttribute("currentUser", currentUser); 
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "You are registered successfully. Please Login.");
        return "redirect:/login";  // Use redirect so the success message appears after redirect
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }
}
