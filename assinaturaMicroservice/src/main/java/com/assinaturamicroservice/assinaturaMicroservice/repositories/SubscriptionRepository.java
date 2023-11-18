package com.assinaturamicroservice.assinaturaMicroservice.repositories;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscriptionId(Long id);

    Optional<Subscription> findSubscriptionByUserId(String userId);
}
