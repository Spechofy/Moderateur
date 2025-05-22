package com.example.Moderateur.controller;

import com.example.Moderateur.model.Motif;
import com.example.Moderateur.services.MotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motifs")
public class MotifController {

    @Autowired
    private MotifService motifService;

    @PostMapping
    public ResponseEntity<Motif> create(@RequestBody Motif motif) {
        return ResponseEntity.ok(motifService.create(motif));
    }

    @GetMapping
    public ResponseEntity<List<Motif>> getAll() {
        return ResponseEntity.ok(motifService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motif> getById(@PathVariable String id) {
        return motifService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        motifService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

