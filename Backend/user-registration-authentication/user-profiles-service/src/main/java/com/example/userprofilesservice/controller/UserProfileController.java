package com.example.userprofilesservice.controller;

import com.example.userprofilesservice.model.User;
import com.example.userprofilesservice.service.UserProfileService;
import com.example.userprofilesservice.util.exception.UserAlreadyExistsException;
import com.example.userprofilesservice.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userProfileService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            User result = userProfileService.addUser(user);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException e){
            return new ResponseEntity<String>("User already exists in the database.", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable("userId") String userId) {
        try {
            User user = userProfileService.findUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}