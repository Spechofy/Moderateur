package com.example.Moderateur.repository;

import com.example.Moderateur.model.Moderateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ModerateurRepositoryTest {

    @Autowired
    private ModerateurRepository moderateurRepository;

    @Test
    public void testSaveAndFindById() {
        Moderateur m = new Moderateur();
        m.setUserId("user123");
        m.setProfileId("profileABC");

        moderateurRepository.save(m);
        Moderateur found = moderateurRepository.findById("user123").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getProfileId()).isEqualTo("profileABC");
    }
}
