package com.example.Moderateur.repository;

import com.example.Moderateur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
