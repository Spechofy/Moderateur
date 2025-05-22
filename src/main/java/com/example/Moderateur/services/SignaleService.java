package com.example.Moderateur.services;

import com.example.Moderateur.model.Signale;
import com.example.Moderateur.repository.SignaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignaleService {

    @Autowired
    private SignaleRepository signaleRepository;

    public Signale create(Signale signale) {
        return signaleRepository.save(signale);
    }

    public List<Signale> getAll() {
        return signaleRepository.findAll();
    }

    public Optional<Signale> getById(String id) {
        return signaleRepository.findById(id);
    }

    public void delete(String id) {
        signaleRepository.deleteById(id);
    }
}

