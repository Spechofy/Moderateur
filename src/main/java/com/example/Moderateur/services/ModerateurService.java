package com.example.Moderateur.services;

import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.repository.ModerateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModerateurService {

    @Autowired
    private ModerateurRepository moderateurRepository;

    public Moderateur saveModerateur(Moderateur moderateur) {
        return moderateurRepository.save(moderateur);
    }

    public void deleteModerateur(Long id) {
        moderateurRepository.deleteById(id);
    }
}
