package com.example.Moderateur.services;

import com.example.Moderateur.kafka.Topics;
import com.example.Moderateur.kafka.event.Action;
import com.example.Moderateur.kafka.event.CommentKafkaEvent;
import com.example.Moderateur.model.Comment;
import com.example.Moderateur.repository.CommentRepository;
import com.example.Moderateur.services.google_api.CommentAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Comment service.
 */
@Service
public class CommentService {
    private final CommentAnalyzer commentAnalyzer = new CommentAnalyzer();

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private KafkaTemplate<String, CommentKafkaEvent> kafkaTemplate;


    /**
     * Create comment.
     *
     * @param comment the comment
     * @return the comment
     */
    public Comment create(Comment comment) {
        double toxicity = commentAnalyzer.analyzeToxicity(comment.getContent());

        // Si le commentaire est toxique (au-dessus du seuil), on le bloque
        if (toxicity > CommentAnalyzer.MIN_TOXICITY) {
            comment.setContent("Ce commentaire est inapproprié");
            return comment;
        }


        kafkaTemplate.send(Topics.COMMENT, new CommentKafkaEvent(Action.CREATE, comment));
        return comment;
    }


    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Comment> getById(String id) {
        return commentRepository.findById(id);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
        commentRepository.deleteById(id);
    }
}

