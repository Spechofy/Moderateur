package com.example.Moderateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "moderateur")
@Getter
@Setter
public class Moderateur {
    @Id
    private Long userId;

    private Long ProfileId;
}
