package com.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvp.model.Contact;
import com.mvp.service.ContactService;

@Controller
public class ContactController {

    @Autowired
    private ContactService ContactService;

    @PostMapping("/submitContactForm")
    public String submitContact(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String subject,
                                     @RequestParam String message,
                                     RedirectAttributes redirectAttributes) {

        Contact Contact = new Contact();
        Contact.setName(name);
        Contact.setEmail(email);
        Contact.setSubject(subject);
        Contact.setMessage(message);

        ContactService.saveContact(Contact);

        redirectAttributes.addFlashAttribute("message", "Your message has been sent. Thank you!");
        return "redirect:/contact";  // Redirect back to the contact page
    }
}
