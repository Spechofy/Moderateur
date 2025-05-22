package com.example.Moderateur.kafka.event;

import com.example.Moderateur.model.Moderateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModerateurKafkaEvent {
    private Action action;
    private Moderateur data;
}
