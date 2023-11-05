package com.assinaturamicroservice.assinaturaMicroservice.controller;


import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlans(){
        List<Plan> allPlans= planService.getAllPlans();
        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id){
        Plan newPlan = planService.getPlanByid(id);
        return new ResponseEntity<>(newPlan, HttpStatus.OK);
    }



}
