package com.assinaturamicroservice.assinaturaMicroservice.service;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    @Autowired
    private PlanRepository repository;

    public void savePlan(Plan plan){
        this.repository.save(plan);
    }

    public Plan createPlan(PlanDTO planDTO){
        Plan newPlan = new Plan(planDTO);
        this.savePlan(newPlan);
        return newPlan;
    }

}
