package com.assinaturamicroservice.assinaturaMicroservice.controller;


import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub")
public class SubscriptionController {

    @Autowired
    private PlanService planService;

    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody PlanDTO planData){
        Plan newPlan = planService.createPlan(planData);
        return new ResponseEntity<>(newPlan, HttpStatus.CREATED);
    }

}
