package com.example.authenticationservice.Controller;

import com.example.authenticationservice.model.User;
import com.example.authenticationservice.service.UserAuthService;
import com.example.authenticationservice.util.exception.UserAlreadyExistsException;
import com.example.authenticationservice.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws UserAlreadyExistsException {
        try{
            User result = userAuthService.register(user);
            String jwtToken = userAuthService.generateToken(result.getUserId());
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwtToken);
            map.put("userId", result.getUserId());
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException e){
            return new ResponseEntity<String>("User already exists in the database.", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException {
        try{
            User result = userAuthService.login(user);
            String jwtToken = userAuthService.generateToken(result.getUserId());
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwtToken);
            map.put("userId", result.getUserId());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
        }
    }
}
