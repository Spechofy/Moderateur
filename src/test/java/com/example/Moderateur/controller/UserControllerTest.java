package com.example.Moderateur.controller;

import com.example.Moderateur.model.User;
import com.example.Moderateur.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = new User();
        user.setUserId("user123");
        user.setBirthday(LocalDateTime.of(2000, 1, 1, 0, 0));
    }

    @Test
    void testCreateUser() {
        when(userService.create(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.create(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("user123", response.getBody().getUserId());
        verify(userService).create(user);
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAll()).thenReturn(List.of(user));

        ResponseEntity<List<User>> response = userController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(userService).getAll();
    }

    @Test
    void testGetUserById_found() {
        when(userService.getById("user123")).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getById("user123");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("user123", response.getBody().getUserId());
        verify(userService).getById("user123");
    }

    @Test
    void testGetUserById_notFound() {
        when(userService.getById("unknown")).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getById("unknown");

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(userService).getById("unknown");
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).delete("user123");

        ResponseEntity<Void> response = userController.delete("user123");

        assertEquals(204, response.getStatusCodeValue());
        verify(userService).delete("user123");
    }
}
