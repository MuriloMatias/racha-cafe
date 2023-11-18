package com.assinaturamicroservice.assinaturaMicroservice.dtos;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;

public record SubscriptionDTO(String userId, Plan plan) {}
