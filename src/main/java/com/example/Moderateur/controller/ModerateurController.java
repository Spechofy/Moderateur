package com.example.Moderateur.controller;

import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.services.ModerateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moderateurs")
public class ModerateurController {

    @Autowired
    private ModerateurService moderateurService;

    @PostMapping
    public ResponseEntity<Moderateur> createModerateur(@RequestBody Moderateur moderateur) {
        return ResponseEntity.ok(moderateurService.saveModerateur(moderateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModerateur(@PathVariable Long id) {
        moderateurService.deleteModerateur(id);
        return ResponseEntity.noContent().build();
    }
}
