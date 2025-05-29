package com.example.Moderateur.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class SignaleRepositoryTest {

    @Autowired
    private SignaleRepository signaleRepository;

}
