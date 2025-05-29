package com.example.Moderateur.services;

import com.example.Moderateur.model.Motif;
import com.example.Moderateur.repository.MotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Motif service.
 */
@Service
public class MotifService {

    @Autowired
    private MotifRepository motifRepository;

    /**
     * Create motif.
     *
     * @param motif the motif
     * @return the motif
     */
    public Motif create(Motif motif) {
        return motifRepository.save(motif);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Motif> getAll() {
        return motifRepository.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Motif> getById(String id) {
        return motifRepository.findById(id);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
        motifRepository.deleteById(id);
    }
}

