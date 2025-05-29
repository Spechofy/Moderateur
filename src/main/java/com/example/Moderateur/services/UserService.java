package com.example.Moderateur.services;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.UserKafkaEvent;
import com.example.Moderateur.model.User;
import com.example.Moderateur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, UserKafkaEvent> kafkaTemplate;


    /**
     * Create user.
     *
     * @param user the user
     * @return the user
     */
    public User create(User user) {
         kafkaTemplate.send(Topics.USER, new UserKafkaEvent(Action.CREATE, user));
         return user;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<User> getById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}

