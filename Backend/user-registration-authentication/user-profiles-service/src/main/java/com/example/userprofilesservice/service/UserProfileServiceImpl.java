package com.example.userprofilesservice.service;

import com.example.userprofilesservice.model.User;
import com.example.userprofilesservice.repository.UserProfileRepository;
import com.example.userprofilesservice.util.exception.UserAlreadyExistsException;
import com.example.userprofilesservice.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        User result = userProfileRepository.findByEmail(user.getEmail());

        if (result != null){
            throw new UserAlreadyExistsException();
        }
        return userProfileRepository.save(new User(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName()));
    }

    @Override
    public List<User> getAllUsers() {
        return userProfileRepository.findAll();
    }

    @Override
    public User findUserById(String userId) throws UserNotFoundException {
        Optional<User> optionalUser = userProfileRepository.findById(userId);

        if (optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

}