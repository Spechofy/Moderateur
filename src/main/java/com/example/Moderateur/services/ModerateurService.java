package com.example.Moderateur.services;

import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.repository.ModerateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModerateurService {

    @Autowired
    private ModerateurRepository moderateurRepository;

    public Moderateur create(Moderateur moderateur) {
        return moderateurRepository.save(moderateur);
    }

    public List<Moderateur> getAll() {
        return moderateurRepository.findAll();
    }

    public Optional<Moderateur> getById(String id) {
        return moderateurRepository.findById(id);
    }

    public void delete(String id) {
        moderateurRepository.deleteById(id);
    }
}

