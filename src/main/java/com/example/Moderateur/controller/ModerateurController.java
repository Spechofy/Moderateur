package com.example.Moderateur.controller;

import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.services.ModerateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Moderateur controller.
 */
@RestController
@RequestMapping("/api/moderateurs")
public class ModerateurController {

    @Autowired
    private ModerateurService moderateurService;

    /**
     * Create response entity.
     *
     * @param moderateur the moderateur
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Moderateur> create(@RequestBody Moderateur moderateur) {
        return ResponseEntity.ok(moderateurService.create(moderateur));
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public ResponseEntity<List<Moderateur>> getAll() {
        return ResponseEntity.ok(moderateurService.getAll());
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Moderateur> getById(@PathVariable String id) {
        return moderateurService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        moderateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

