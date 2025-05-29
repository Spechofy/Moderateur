package com.example.Moderateur.repository;

import com.example.Moderateur.model.Motif;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MotifRepositoryTest {

    @Autowired
    private MotifRepository motifRepository;

   // @Test
    public void testSaveAndFindById() {
        Motif motif = new Motif("M001", "contenu inapproprié");

        motifRepository.save(motif);

        Motif found = motifRepository.findById("M001").orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getLabel()).isEqualTo("contenu inapproprié");
    }
}
