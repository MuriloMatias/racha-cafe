package com.assinaturamicroservice.assinaturaMicroservice.controller;


import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.User;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.UserDTO;
import com.assinaturamicroservice.assinaturaMicroservice.service.PlanService;
import com.assinaturamicroservice.assinaturaMicroservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="Assinatura", description = "Endpoints de plano e de assinatura")
public class SubscriptionController {

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @PostMapping("/plan")
    @Operation(summary = "Criação de plano")
    public ResponseEntity<Plan> createPlan(@RequestBody PlanDTO planData) {
        Plan newPlan = planService.createPlan(planData);
        return new ResponseEntity<>(newPlan, HttpStatus.CREATED);
    }

    @GetMapping("plan")
    @Operation(summary = "Buscar todos os planos")
    public ResponseEntity<List<Plan>> getAllPlans() {
        List<Plan> allPlans = planService.getAllPlans();
        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @GetMapping("/plan/{id}")
    @Operation(summary = "Buscar planos por id")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        Plan newPlan = planService.getPlanByid(id);
        return new ResponseEntity<>(newPlan, HttpStatus.OK);
    }

    @DeleteMapping("/plan/{id}")
    @Transactional
    @Operation(summary = "Deletar planos")
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/plan/{id}")
    @Transactional
    @Operation(summary = "Atualizar planos")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody PlanDTO data) {
        Plan updatePlan = planService.updatePlan(id, data);
        return new ResponseEntity<>(updatePlan, HttpStatus.OK);
    }

    @PostMapping("/user")
    @Operation(summary = "Criar assinatura de plano")
    @ApiResponse(responseCode = "201", description = "Assinatura criada com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class), examples = {
                    @ExampleObject(value = "{\"id\": 1, \"name\": \"José\", \"email\": \"jose@ufms.br\", \"plan\": {\"id\": 1, \"name\": \"Café todo dia\", \"description\": \"Plano específico para um cafézinho no final de tarde\", \"isDeleted\": \"false\"} }")
            })
    })
    public ResponseEntity<User> createUser(@RequestBody UserDTO userData) {
        User newUser = userService.createUser(userData);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    @Operation(summary = "Cancelar assinatura de plano")
    public ResponseEntity<User> canceledUserPlan(@PathVariable Long id) {
        User user = userService.canceledUserPlan(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
