package com.example.authenticationservice.service;

import com.example.authenticationservice.model.User;
import com.example.authenticationservice.repository.UserAuthRepository;
import com.example.authenticationservice.util.exception.UserAlreadyExistsException;
import com.example.authenticationservice.util.exception.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService{

    static Key secret = MacProvider.generateKey();
    private final String secretKey = "secretKey";

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public User register(User user) throws UserAlreadyExistsException {
        User result = userAuthRepository.findByEmail(user.getEmail());

        if (result != null){
            throw new UserAlreadyExistsException();
        }
        return userAuthRepository.save(new User(user.getEmail(), user.getPassword()));
    }

    @Override
    public User login(User user) throws UserNotFoundException {
        User result = userAuthRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (result == null){
            throw new UserNotFoundException();
        }
        return result;
    }

    @Override
    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600000))
                .signWith(SignatureAlgorithm.HS256, "cgicanadakey")
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        String userId = Jwts.parser()
                .setSigningKey("cgicanadakey")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        Optional<User> optionalUser = userAuthRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User not found with token=" + token);
        }
        return true;
    }
}
