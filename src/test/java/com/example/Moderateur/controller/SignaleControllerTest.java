package com.example.Moderateur.controller;

import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.model.Motif;
import com.example.Moderateur.model.Signale;
import com.example.Moderateur.model.User;
import com.example.Moderateur.services.SignaleService;
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
class SignaleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SignaleService signaleService;

    @InjectMocks
    private SignaleController signaleController;

    private Signale signale;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(signaleController).build();

        // Création d'un signale avec relations fictives
        User user = new User();
        user.setUserId("user1");

        Moderateur moderateur = new Moderateur();
        moderateur.setUserId(user.getUserId());

        Motif motif = new Motif();
        motif.setId("motif1");

        signale = new Signale();
        signale.setId(1L);
        signale.setUser(user);
        signale.setModerateur(moderateur);
        signale.setMotif(motif);
        signale.setDateSignalement(LocalDateTime.now());
        signale.setCommentaire("Contenu signalé");
    }

    @Test
    void testCreateSignale() {
        when(signaleService.create(any(Signale.class))).thenReturn(signale);

        ResponseEntity<Signale> response = signaleController.create(signale);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(signaleService).create(signale);
    }

    @Test
    void testGetAllSignales() {
        when(signaleService.getAll()).thenReturn(List.of(signale));

        ResponseEntity<List<Signale>> response = signaleController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(signaleService).getAll();
    }

    @Test
    void testGetSignaleById_found() {
        when(signaleService.getById("1")).thenReturn(Optional.of(signale));

        ResponseEntity<Signale> response = signaleController.getById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(signale, response.getBody());
        verify(signaleService).getById("1");
    }

    @Test
    void testGetSignaleById_notFound() {
        when(signaleService.getById("99")).thenReturn(Optional.empty());

        ResponseEntity<Signale> response = signaleController.getById("99");

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(signaleService).getById("99");
    }

    @Test
    void testDeleteSignale() {
        doNothing().when(signaleService).delete("1");

        ResponseEntity<Void> response = signaleController.delete("1");

        assertEquals(204, response.getStatusCodeValue());
        verify(signaleService).delete("1");
    }
}
