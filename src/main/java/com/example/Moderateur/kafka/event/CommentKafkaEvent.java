package com.example.Moderateur.kafka.event;

import com.example.Moderateur.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentKafkaEvent {
    private Action action;
    private Comment data;
}
