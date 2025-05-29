package com.example.Moderateur.controller;

import com.example.Moderateur.model.Comment;
import com.example.Moderateur.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private Comment comment;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();

        comment = new Comment();
        comment.setId("cmt1");
        comment.setUserId("user123");
        comment.setContent("This is a test comment");
    }

    @Test
    void testCreateComment() {
        when(commentService.create(any(Comment.class))).thenReturn(comment);

        ResponseEntity<Comment> response = commentController.create(comment);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("cmt1", response.getBody().getId());
        verify(commentService).create(comment);
    }

    @Test
    void testGetAllComments() {
        List<Comment> comments = List.of(comment);
        when(commentService.getAll()).thenReturn(comments);

        ResponseEntity<List<Comment>> response = commentController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(commentService).getAll();
    }

    @Test
    void testGetCommentById_found() {
        when(commentService.getById("cmt1")).thenReturn(Optional.of(comment));

        ResponseEntity<Comment> response = commentController.getById("cmt1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(comment, response.getBody());
        verify(commentService).getById("cmt1");
    }

    @Test
    void testGetCommentById_notFound() {
        when(commentService.getById("invalid")).thenReturn(Optional.empty());

        ResponseEntity<Comment> response = commentController.getById("invalid");

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(commentService).getById("invalid");
    }

    @Test
    void testDeleteComment() {
        doNothing().when(commentService).delete("cmt1");

        ResponseEntity<Void> response = commentController.delete("cmt1");

        assertEquals(204, response.getStatusCodeValue());
        verify(commentService).delete("cmt1");
    }
}
