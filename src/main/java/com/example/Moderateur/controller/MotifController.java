package com.example.Moderateur.controller;

import com.example.Moderateur.model.Motif;
import com.example.Moderateur.services.MotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Motif controller.
 */
@RestController
@RequestMapping("/api/motifs")
public class MotifController {

    @Autowired
    private MotifService motifService;

    /**
     * Create response entity.
     *
     * @param motif the motif
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Motif> create(@RequestBody Motif motif) {
        return ResponseEntity.ok(motifService.create(motif));
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public ResponseEntity<List<Motif>> getAll() {
        return ResponseEntity.ok(motifService.getAll());
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Motif> getById(@PathVariable String id) {
        return motifService.getById(id)
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
        motifService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

