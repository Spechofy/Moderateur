package com.example.Moderateur.services;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.SignaleKafkaEvent;
import com.example.Moderateur.model.Signale;
import com.example.Moderateur.repository.SignaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Signale service.
 */
@Service
public class SignaleService {

    @Autowired
    private SignaleRepository signaleRepository;

    @Autowired
    private KafkaTemplate<String, SignaleKafkaEvent> kafkaTemplate;

    /**
     * Create signale.
     *
     * @param signale the signale
     * @return the signale
     */
    public Signale create(Signale signale) {
        kafkaTemplate.send(Topics.SIGNALE, new SignaleKafkaEvent(Action.CREATE, signale));
        return signaleRepository.save(signale);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Signale> getAll() {
        return signaleRepository.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Signale> getById(String id) {
        return signaleRepository.findById(Long.parseLong(id));
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
        signaleRepository.deleteById(Long.parseLong(id));
    }
}

