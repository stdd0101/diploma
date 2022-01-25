package com.example.filestorageapp.service;

import com.example.filestorageapp.domain.User;

public interface AuthService {
    String getToken(User user);
    String getRefreshToken(User user);
    User getAuthUser(String username);
    String getUsernameFromToken(String token);
    String refreshToken(String token);
}
