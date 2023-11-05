package com.assinaturamicroservice.assinaturaMicroservice.repositories;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {}
