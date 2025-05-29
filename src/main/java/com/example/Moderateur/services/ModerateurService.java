package com.example.Moderateur.services;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.ModerateurKafkaEvent;
import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.repository.ModerateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Moderateur service.
 */
@Service
public class ModerateurService {

    @Autowired
    private ModerateurRepository moderateurRepository;
    @Autowired
    private KafkaTemplate<String, ModerateurKafkaEvent> kafkaTemplate;


    /**
     * Create moderateur.
     *
     * @param moderateur the moderateur
     * @return the moderateur
     */
    public Moderateur create(Moderateur moderateur) {
        kafkaTemplate.send(Topics.MODERATEUR, new ModerateurKafkaEvent(Action.CREATE, moderateur));
        return moderateur;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Moderateur> getAll() {
        return moderateurRepository.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Moderateur> getById(String id) {
        return moderateurRepository.findById(id);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
        moderateurRepository.deleteById(id);
    }
}

