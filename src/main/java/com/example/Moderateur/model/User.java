package com.example.Moderateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@Setter
public class User {
    @Id
    private String userId;
    private LocalDateTime birthday;
}

