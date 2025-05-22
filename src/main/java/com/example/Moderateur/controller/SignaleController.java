package com.example.Moderateur.controller;

import com.example.Moderateur.model.Signale;
import com.example.Moderateur.services.SignaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signales")
public class SignaleController {

    @Autowired
    private SignaleService signaleService;

    @PostMapping
    public ResponseEntity<Signale> create(@RequestBody Signale signale) {
        return ResponseEntity.ok(signaleService.create(signale));
    }

    @GetMapping
    public ResponseEntity<List<Signale>> getAll() {
        return ResponseEntity.ok(signaleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Signale> getById(@PathVariable String id) {
        return signaleService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        signaleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

