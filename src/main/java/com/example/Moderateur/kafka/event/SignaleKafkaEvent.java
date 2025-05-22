package com.example.Moderateur.kafka.event;

import com.example.Moderateur.model.Signale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignaleKafkaEvent {
    private Action action;
    private Signale data;
}
