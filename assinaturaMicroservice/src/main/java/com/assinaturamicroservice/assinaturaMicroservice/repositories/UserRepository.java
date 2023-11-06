package com.assinaturamicroservice.assinaturaMicroservice.repositories;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(Long id);
}
