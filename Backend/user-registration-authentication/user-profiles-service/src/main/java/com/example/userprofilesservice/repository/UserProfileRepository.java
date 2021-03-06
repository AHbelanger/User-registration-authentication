package com.example.userprofilesservice.repository;

import com.example.userprofilesservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
