package com.example.authenticationservice.service;

import com.example.authenticationservice.model.User;
import com.example.authenticationservice.util.exception.UserAlreadyExistsException;
import com.example.authenticationservice.util.exception.UserNotFoundException;

public interface UserAuthService {

    User register(User user) throws UserAlreadyExistsException;
    User login(User user) throws UserNotFoundException;
    String generateToken(String email);
    boolean validateToken(String token);
}
