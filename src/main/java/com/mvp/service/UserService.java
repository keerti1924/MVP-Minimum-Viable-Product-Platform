package com.mvp.service;

import com.mvp.model.User;

public interface UserService {
    void saveUser(User user);
    User findByEmailAndPassword(String email, String password); // Updated
	User findByUsername(String username);
}
