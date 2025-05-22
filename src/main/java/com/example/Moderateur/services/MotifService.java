package com.example.Moderateur.services;

import com.example.Moderateur.model.Motif;
import com.example.Moderateur.repository.MotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotifService {

    @Autowired
    private MotifRepository motifRepository;

    public Motif create(Motif motif) {
        return motifRepository.save(motif);
    }

    public List<Motif> getAll() {
        return motifRepository.findAll();
    }

    public Optional<Motif> getById(String id) {
        return motifRepository.findById(id);
    }

    public void delete(String id) {
        motifRepository.deleteById(id);
    }
}

