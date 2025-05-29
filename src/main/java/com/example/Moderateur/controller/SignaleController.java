package com.example.Moderateur.controller;

import com.example.Moderateur.model.Signale;
import com.example.Moderateur.services.SignaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Signale controller.
 */
@RestController
@RequestMapping("/api/signales")
public class SignaleController {

    @Autowired
    private SignaleService signaleService;

    /**
     * Create response entity.
     *
     * @param signale the signale
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Signale> create(@RequestBody Signale signale) {
        return ResponseEntity.ok(signaleService.create(signale));
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public ResponseEntity<List<Signale>> getAll() {
        return ResponseEntity.ok(signaleService.getAll());
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Signale> getById(@PathVariable String id) {
        return signaleService.getById(id)
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
        signaleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

