package com.mvp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvp.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
