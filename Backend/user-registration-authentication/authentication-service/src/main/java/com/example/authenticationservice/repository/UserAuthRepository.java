package com.example.authenticationservice.repository;

import com.example.authenticationservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends MongoRepository<User,String> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
