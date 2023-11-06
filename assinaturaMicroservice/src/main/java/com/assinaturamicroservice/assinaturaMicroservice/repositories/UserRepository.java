package com.assinaturamicroservice.assinaturaMicroservice.repositories;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
