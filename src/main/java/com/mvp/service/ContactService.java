package com.mvp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvp.model.Contact;
import com.mvp.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
    private ContactRepository contactRepository;

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }
}
