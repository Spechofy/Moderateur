package com.example.Moderateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "/api/comments")
public class Comment {
    @Id
    private String id;
    private String userId;
    private String content;

}
