package com.example.Moderateur.kafka.consumer;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.UserKafkaEvent;
import com.example.Moderateur.model.User;
import com.example.Moderateur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaConsumer {
    @Autowired
    private UserRepository userRepository;


    @KafkaListener(topics = Topics.USER, groupId = "spechofy-group")
    public void consumeUser(UserKafkaEvent event) {
        Action action = event.getAction();
        User user = event.getData();
        switch (action) {
            case CREATE: userRepository.save(user); break;
        }
    }
}
