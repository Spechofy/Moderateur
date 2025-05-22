package com.example.Moderateur.kafka.consumer;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.SignaleKafkaEvent;
import com.example.Moderateur.model.Signale;
import com.example.Moderateur.repository.SignaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SignaleKafkaConsumer {
    @Autowired
    private SignaleRepository signaleRepository;


    @KafkaListener(topics = Topics.SIGNALE, groupId = "spechofy-group")
    public void consumeSignale(SignaleKafkaEvent event) {
        Action action = event.getAction();
        Signale signale = event.getData();
        switch (action) {
            case CREATE: signaleRepository.save(signale); break;
        }
    }
}
