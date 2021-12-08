package com.example.filestorageapp.service;

import com.example.filestorageapp.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(String username);
    List<User> getUsers();
}
