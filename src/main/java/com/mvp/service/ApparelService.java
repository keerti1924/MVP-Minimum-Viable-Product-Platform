package com.mvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvp.model.Apparel;
import com.mvp.model.User;
import com.mvp.repository.ApparelRepository;

@Service
public class ApparelService {
	@Autowired
    private ApparelRepository apparelRepository;

    public List<Apparel> getAllApparels() {
        return apparelRepository.findAll();
    }

    public Apparel saveApparel(Apparel apparel) {
        return apparelRepository.save(apparel);
    }

    public Apparel getApparelById(Long id) {
        return apparelRepository.findById(id).orElse(null);
    }

    public void deleteApparel(Long id) {
        apparelRepository.deleteById(id);
    }
    
    public List<Apparel> getApparelsByUser(User user) {
        return apparelRepository.findByUser(user); // Assuming you have this method defined in your repository
    }
    
    public List<Apparel> getTopApparels(int limit) {
        return apparelRepository.findTopNApparels(limit); // Adjust this method to your repository
    }


}