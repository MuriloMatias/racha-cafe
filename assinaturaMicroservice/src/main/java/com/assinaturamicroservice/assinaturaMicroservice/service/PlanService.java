package com.assinaturamicroservice.assinaturaMicroservice.service;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Plan getPlanByid(long id){
        Optional<Plan> plan = this.repository.findById(id);
        if(plan.isPresent() && !plan.get().getIsDeleted()){
            return plan.get();
        }
        throw new NoSuchElementException("Plano");
    }

    public List<Plan> getAllPlans(){
        Plan filter = new Plan();
        filter.setIsDeleted(Boolean.FALSE);
        return this.repository.findAll(Example.of(filter));
    }

    public void deletePlan( long id){
        Plan plan = getPlanByid(id);
        plan.setIsDeleted(Boolean.TRUE);
    }

    public Plan updatePlan(Long id, PlanDTO updatePlan){
        Plan oldPlan = getPlanByid(id);

        if(updatePlan.name() != null){
            oldPlan.setName(updatePlan.name());
        }

        if (updatePlan.description() != null){
            oldPlan.setDescription(updatePlan.description());
        }

        return oldPlan;
    }
}


