package com.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvp.model.Apparel;
import com.mvp.model.User;
import com.mvp.repository.ApparelRepository;
import com.mvp.service.ApparelService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import com.mvp.util.TimeUtils;

@Controller
@RequestMapping("/apparel")
@SessionAttributes("currentUser")
public class ApparelController {

    @Autowired
    private ApparelService apparelService;
    
    @Autowired
    private ApparelRepository apparelRepository;

    @GetMapping("/new")
    public String submitApparel(Model model) {
        model.addAttribute("apparel", new Apparel());
        return "create";
    }
    
    @GetMapping("/all")
    public String AllApparel(@SessionAttribute(name = "currentUser" , required = false) User currentUser, Model model) {
    	model.addAttribute("currentUser", currentUser); 
    	List<Apparel> apparels = apparelService.getAllApparels();
		model.addAttribute("apparels", apparels);
        return "apparels";
    }

    @PostMapping("/save")
    public String saveApparel(@ModelAttribute("apparel") Apparel apparel,
            @RequestParam("image") MultipartFile imageFile,
            @SessionAttribute("currentUser") User currentUser,
            RedirectAttributes redirectAttributes) {

		// Set the user of the apparel
		 apparel.setUser(currentUser);
		
		 apparel.setPostedDate(new Date());
		 try {
		        // Convert the MultipartFile to a byte array
		        byte[] imageBytes = imageFile.getBytes();
		        
		        // Set the image data in the apparel object
		        apparel.setImagePath(imageBytes);

		        // Save the apparel to the database
		        apparel.setStatus("Pending");
		        apparelRepository.save(apparel);
		        
		        redirectAttributes.addFlashAttribute("message", "Apparel saved successfully!");
		    } catch (IOException e) {
		    	redirectAttributes.addFlashAttribute("message", "Error saving apparel: " + e.getMessage());
		    }

		return "redirect:/apparel/profile";  // Redirect to homepage or a success page
	}

    
    @GetMapping("/edit/{id}")
    public String editApparel(@PathVariable Long id, Model model) {
        Apparel apparel = apparelService.getApparelById(id); // Fetch apparel by ID
        model.addAttribute("apparel", apparel); // Add to model
        return "edit-apparel"; // Return edit-apparel.html
    }

    @PostMapping("/edit/{id}")
    public String updateApparel(@PathVariable Long id, @ModelAttribute Apparel apparel,
                                @RequestParam("image") MultipartFile imageFile,
                                @SessionAttribute("currentUser") User currentUser,
                                RedirectAttributes redirectAttributes) {
        apparel.setId(id);
        apparel.setUser(currentUser);
        apparel.setPostedDate(new Date());
        
        // Check if an image was uploaded
        if (!imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                apparel.setImagePath(imageBytes); // Update image
            } catch (IOException e) {
                // Handle error
            }
        }
        redirectAttributes.addFlashAttribute("message", "Apparel Updated Successfully");
        apparelService.saveApparel(apparel); // Save the updated apparel
        return "redirect:/apparel/profile"; // Redirect to user's apparel list
    }


    @GetMapping("/delete/{id}")
    public String deleteApparel(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        apparelService.deleteApparel(id); 
        redirectAttributes.addFlashAttribute("message", "Apparel Deleted Successfully");
        return "redirect:/apparel/profile"; 
    }

    
    @GetMapping("/profile")
    public String myApparel(Model model, @ModelAttribute("currentUser") User currentUser) {
    	List<Apparel> userApparels = apparelService.getApparelsByUser(currentUser);
        model.addAttribute("userApparels", userApparels);
    	return "profile";
    }
    
    @GetMapping("/{id}/image")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Apparel apparel = apparelRepository.findById(id).orElse(null);
        if (apparel != null && apparel.getImagePath() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust according to your image type
                    .body(apparel.getImagePath());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public String getApparelDetails(@PathVariable Long id, Model model) {
        Apparel apparel = apparelService.getApparelById(id);
        String timeAgo = TimeUtils.timeAgo(apparel.getPostedDate());
        model.addAttribute("apparel", apparel);
        model.addAttribute("timeAgo", timeAgo);
        return "apparel-details";  
    }

}
