package com.example.Moderateur.repository;

import com.example.Moderateur.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
