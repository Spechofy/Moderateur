package com.example.Moderateur.kafka.consumer;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.ModerateurKafkaEvent;
import com.example.Moderateur.model.Moderateur;
import com.example.Moderateur.repository.ModerateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * The type Moderateur kafka consumer.
 */
@Service
public class ModerateurKafkaConsumer {
    @Autowired
    private ModerateurRepository moderateurRepository;


    /**
     * Consume moderateur.
     *
     * @param event the event
     */
    @KafkaListener(
            topics = Topics.MODERATEUR,
            groupId = "moderateur-group",
            containerFactory = "moderateurKafkaListenerFactory"
    )
    public void consumeModerateur(ModerateurKafkaEvent event) {
        Action action = event.getAction();
        Moderateur moderateur = event.getData();
        switch (action) {
            case CREATE: moderateurRepository.save(moderateur); break;
            case UPDATE: moderateurRepository.save(moderateur); break;
            case DELETE: moderateurRepository.delete(moderateur); break;
            default: break;
        }
    }
}
