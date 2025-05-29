package com.example.Moderateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "moderateur")
@Getter
@Setter
public class Moderateur {
    @Id
    private String userId;

    private String profileId;
}
