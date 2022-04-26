package com.example.authenticationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class User {

    @Id
    private String userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}
