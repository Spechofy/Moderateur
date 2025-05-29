package com.example.Moderateur.controller;

import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.services.ModerateurService;
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
class ModerateurControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ModerateurService moderateurService;

    @InjectMocks
    private ModerateurController moderateurController;

    private Moderateur moderateur;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(moderateurController).build();

        moderateur = new Moderateur();
        moderateur.setUserId("user1");
        moderateur.setProfileId("profil1");
    }

    @Test
    void testCreateModerateur() {
        when(moderateurService.create(any(Moderateur.class))).thenReturn(moderateur);

        ResponseEntity<Moderateur> response = moderateurController.create(moderateur);

        assertEquals(200, response.getStatusCodeValue());
        verify(moderateurService).create(moderateur);
    }

    @Test
    void testGetAllModerateurs() {
        List<Moderateur> list = List.of(moderateur);
        when(moderateurService.getAll()).thenReturn(list);

        ResponseEntity<List<Moderateur>> response = moderateurController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(moderateurService).getAll();
    }

    @Test
    void testGetModerateurById_found() {
        when(moderateurService.getById("mod1")).thenReturn(Optional.of(moderateur));

        ResponseEntity<Moderateur> response = moderateurController.getById("mod1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(moderateur, response.getBody());
        verify(moderateurService).getById("mod1");
    }

    @Test
    void testGetModerateurById_notFound() {
        when(moderateurService.getById("invalid")).thenReturn(Optional.empty());

        ResponseEntity<Moderateur> response = moderateurController.getById("invalid");

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(moderateurService).getById("invalid");
    }

    @Test
    void testDeleteModerateur() {
        doNothing().when(moderateurService).delete("mod1");

        ResponseEntity<Void> response = moderateurController.delete("mod1");

        assertEquals(204, response.getStatusCodeValue());
        verify(moderateurService).delete("mod1");
    }
}
