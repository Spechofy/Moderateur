package com.example.Moderateur.controller;

import com.example.Moderateur.model.Motif;
import com.example.Moderateur.services.MotifService;
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
class MotifControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MotifService motifService;

    @InjectMocks
    private MotifController motifController;

    private Motif motif;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(motifController).build();

        motif = new Motif();
        motif.setId("motif1");
        motif.setLabel("Spam");
    }

    @Test
    void testCreateMotif() {
        when(motifService.create(any(Motif.class))).thenReturn(motif);

        ResponseEntity<Motif> response = motifController.create(motif);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(motif, response.getBody());
        verify(motifService).create(motif);
    }

    @Test
    void testGetAllMotifs() {
        List<Motif> motifs = List.of(motif);
        when(motifService.getAll()).thenReturn(motifs);

        ResponseEntity<List<Motif>> response = motifController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(motifService).getAll();
    }

    @Test
    void testGetMotifById_found() {
        when(motifService.getById("motif1")).thenReturn(Optional.of(motif));

        ResponseEntity<Motif> response = motifController.getById("motif1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(motif, response.getBody());
        verify(motifService).getById("motif1");
    }

    @Test
    void testGetMotifById_notFound() {
        when(motifService.getById("unknown")).thenReturn(Optional.empty());

        ResponseEntity<Motif> response = motifController.getById("unknown");

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(motifService).getById("unknown");
    }

    @Test
    void testDeleteMotif() {
        doNothing().when(motifService).delete("motif1");

        ResponseEntity<Void> response = motifController.delete("motif1");

        assertEquals(204, response.getStatusCodeValue());
        verify(motifService).delete("motif1");
    }
}
