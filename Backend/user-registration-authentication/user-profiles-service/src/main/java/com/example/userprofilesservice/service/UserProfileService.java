package com.example.userprofilesservice.service;

import com.example.userprofilesservice.model.User;
import com.example.userprofilesservice.util.exception.UserAlreadyExistsException;
import com.example.userprofilesservice.util.exception.UserNotFoundException;

import java.util.List;

public interface UserProfileService {
    User addUser(User user) throws UserAlreadyExistsException;
    List<User> getAllUsers();
    User findUserById(String userId) throws UserNotFoundException;
}
