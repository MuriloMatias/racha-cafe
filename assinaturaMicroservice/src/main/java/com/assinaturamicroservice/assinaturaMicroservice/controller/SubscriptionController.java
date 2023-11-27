package com.assinaturamicroservice.assinaturaMicroservice.controller;


import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Subscription;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.idDTO;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.SubscriptionDTO;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.SubscriptionPlanIdDTO;
import com.assinaturamicroservice.assinaturaMicroservice.service.PlanService;
import com.assinaturamicroservice.assinaturaMicroservice.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="Assinatura", description = "Endpoints de plano e de assinatura")
public class SubscriptionController {

    @Autowired
    private PlanService planService;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/plan")
    @PreAuthorize("hasRole('default-roles-facoffee')")
    @Operation(summary = "Criação de plano", security = @SecurityRequirement(name = "bearerAuth"))
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
    @PreAuthorize("hasRole('default-roles-facoffee')")
    @Operation(summary = "Deletar planos", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/plan/{id}")
    @Transactional
    @PreAuthorize("hasRole('default-roles-facoffee')")
    @Operation(summary = "Atualizar planos", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody PlanDTO data) {
        Plan updatePlan = planService.updatePlan(id, data);
        return new ResponseEntity<>(updatePlan, HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    @Operation(summary = "Criar assinatura de plano", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('default-roles-facoffee')")
    @ApiResponse(responseCode = "201", description = "Assinatura criada com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionDTO.class), examples = {
                    @ExampleObject(value = "{\"subscriptionId\": 1, \"userId\": \"userId\", \"plan\": {\"id\": 1, \"name\": \"Café todo dia\", \"description\": \"Plano específico para um cafézinho no final de tarde\", \"isDeleted\": \"false\"} }")
            })
    })
    public ResponseEntity<Subscription> createSubscribe(@AuthenticationPrincipal Jwt jwt, @RequestBody idDTO planId) {
        String userId = jwt.getSubject();
        Plan plan = new Plan();
        plan.setId(planId.planId());
        SubscriptionDTO subscriptionData = new SubscriptionDTO(userId, plan);
        Subscription newSubscription = subscriptionService.createSubscription(subscriptionData);
        return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);
    }

    @PatchMapping("/subscribe/{subscriptionId}")
    @Transactional
    @Operation(summary = "Cancelar assinatura de plano", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('default-roles-facoffee')")
    public ResponseEntity<Subscription> canceledSubcription(@PathVariable Long subscriptionId) {
        Subscription subscription = subscriptionService.canceledSubcription(subscriptionId);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/subscribe/change_plan")
    @Transactional
    @Operation(summary = "Alterar plano da assinatura", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('default-roles-facoffee')")
    public ResponseEntity<Subscription> changeSubcriptionPlan(@RequestBody SubscriptionPlanIdDTO subscriptionPlanIdDTO){
        Long planId = Long.parseLong(subscriptionPlanIdDTO.id());
        Subscription subscription = subscriptionService.changeSubscriptionPlan(planId, subscriptionPlanIdDTO.planId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
