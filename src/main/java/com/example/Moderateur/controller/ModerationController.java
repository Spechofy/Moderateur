package com.example.Moderateur.controller;

import com.example.Moderateur.model.ReportedComment;
import com.example.Moderateur.services.ModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moderation")
public class ModerationController {

    @Autowired
    private ModerationService moderationService;

    // Vérifie l’âge d’un utilisateur
    @GetMapping("/check-age/{id}")
    public ResponseEntity<Void> checkAge(@PathVariable Long id) {
        moderationService.verifyUserAge(id);
        return ResponseEntity.ok().build();
    }

    // Modération d’un commentaire signalé
    @PostMapping("/comment")
    public ResponseEntity<Void> checkComment(@RequestBody ReportedComment dto) {
        moderationService.handleReportedComment(dto);
        return ResponseEntity.ok().build();
    }

    // Suspendre un utilisateur signalé
    @PostMapping("/suspend-user")
    public ResponseEntity<Void> suspendUser(@RequestParam Long userId, @RequestParam String motif) {
        moderationService.handleReportedUser(userId, motif);
        return ResponseEntity.ok().build();
    }
}
