package com.assinaturamicroservice.assinaturaMicroservice.dtos;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;

public record UserDTO(String name, String email, Plan plan) {

}
