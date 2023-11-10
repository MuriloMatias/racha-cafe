package com.assinaturamicroservice.assinaturaMicroservice.controller;


import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.User;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.UserDTO;
import com.assinaturamicroservice.assinaturaMicroservice.service.PlanService;
import com.assinaturamicroservice.assinaturaMicroservice.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @PostMapping("/plan")
    public ResponseEntity<Plan> createPlan(@RequestBody PlanDTO planData) {
        Plan newPlan = planService.createPlan(planData);
        return new ResponseEntity<>(newPlan, HttpStatus.CREATED);
    }

    @GetMapping("plan")
    public ResponseEntity<List<Plan>> getAllPlans() {
        List<Plan> allPlans = planService.getAllPlans();
        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @GetMapping("/plan/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        Plan newPlan = planService.getPlanByid(id);
        return new ResponseEntity<>(newPlan, HttpStatus.OK);
    }

    @DeleteMapping("/plan/{id}")
    @Transactional
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/plan/{id}")
    @Transactional
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody PlanDTO data) {
        Plan updatePlan = planService.updatePlan(id, data);
        return new ResponseEntity<>(updatePlan, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userData) {
        User newUser = userService.createUser(userData);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    public ResponseEntity<User> canceledUserPlan(@PathVariable Long id) {
        User user = userService.canceledUserPlan(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
