package com.example.Moderateur.kafka.consumer;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.UserKafkaEvent;
import com.example.Moderateur.model.User;
import com.example.Moderateur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * The type User kafka consumer.
 */
@Service
public class UserKafkaConsumer {
    @Autowired
    private UserRepository userRepository;


    /**
     * Consume user.
     *
     * @param event the event
     */
    @KafkaListener(
            topics = Topics.USER,
            groupId = "moderateur-group",
            containerFactory = "userKafkaListenerFactory"
    )
    public void consumeUser(UserKafkaEvent event) {
        Action action = event.getAction();
        User user = event.getData();
        switch (action) {
            case CREATE: userRepository.save(user); break;
            case UPDATE: userRepository.save(user); break;
            case DELETE: userRepository.delete(user); break;
            default: break;
        }
    }
}
