package com.mvp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mvp.model.Apparel;
import com.mvp.model.User;
import com.mvp.service.ApparelService;

@Controller
public class HomeController {
	@Autowired
    private ApparelService apparelService;
	
    @GetMapping("/")
    public String index(@SessionAttribute(name = "currentUser", required = false) User currentUser,
            Model model) {
    	List<Apparel> apparels = apparelService.getTopApparels(8);
		model.addAttribute("apparels", apparels);
		model.addAttribute("currentUser", currentUser); // Add current user to the model
		return "index"; // Return the index view
	}
    
    @GetMapping("/about")
    public String aboutPage(@SessionAttribute(name = "currentUser", required = false) User currentUser, Model model) {
    	model.addAttribute("currentUser", currentUser); 
    	return "about"; 
    }

    @GetMapping("/contact")
    public String contactPage(@SessionAttribute(name = "currentUser", required = false) User currentUser, Model model) {
    	model.addAttribute("currentUser", currentUser); 
    	return "contact"; 
    }
    

}
