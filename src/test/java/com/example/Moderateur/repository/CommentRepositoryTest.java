package com.example.Moderateur.repository;

import com.example.Moderateur.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void testSaveAndFindById() {
        Comment comment = new Comment();
        comment.setId("cmt123");
        comment.setUserId("user456");
        comment.setContent("Ceci est un commentaire.");

        commentRepository.save(comment);

        Optional<Comment> found = commentRepository.findById("cmt123");

        assertTrue(found.isPresent());
        assertEquals("user456", found.get().getUserId());
        assertEquals("Ceci est un commentaire.", found.get().getContent());
    }

    @Test
    void testDeleteById() {
        Comment comment = new Comment();
        comment.setId("cmt456");
        comment.setUserId("user789");
        comment.setContent("À supprimer.");

        commentRepository.save(comment);
        commentRepository.deleteById("cmt456");

        assertFalse(commentRepository.findById("cmt456").isPresent());
    }
}
