package com.example.Moderateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "moderateur")
public class Moderateur {
    @Id
    private Long userId;

    private Long ProfileId;


    public Long getProfileId() {
        return ProfileId;
    }

    public void setProfileId(Long profileId) {
        ProfileId = profileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
