package com.example.Moderateur.kafka.event;


import com.example.Moderateur.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserKafkaEvent {
    private Action action;
    private User data;
}
