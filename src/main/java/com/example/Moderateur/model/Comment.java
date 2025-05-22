package com.example.Moderateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "comments")
public class Comment {
    @Id
    private Long Id;
    private Long userId;
    private String content;

}
