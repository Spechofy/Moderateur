package com.example.Moderateur.services;

import com.example.Moderateur.model.Comment;
import com.example.Moderateur.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getById(String id) {
        return commentRepository.findById(id);
    }

    public void delete(String id) {
        commentRepository.deleteById(id);
    }
}

