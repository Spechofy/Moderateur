package com.example.Moderateur.kafka.consumer;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.CommentKafkaEvent;
import com.example.Moderateur.model.Comment;
import com.example.Moderateur.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * The type Comment kafka consumer.
 */
@Service
public class CommentKafkaConsumer {

    @Autowired
    private CommentRepository commentRepository;


    /**
     * Consume comment.
     *
     * @param event the event
     */
    @KafkaListener(
            topics = Topics.COMMENT,
            groupId = "moderateur-group",
            containerFactory = "commentKafkaListenerFactory"
    )
    public void consumeComment(CommentKafkaEvent event) {
        Action action = event.getAction();
        Comment comment = event.getData();
        switch (action) {
            case CREATE: commentRepository.save(comment); break;
            case UPDATE: commentRepository.save(comment); break;
            case DELETE: commentRepository.delete(comment); break;
            default: break;
        }
    }

}
