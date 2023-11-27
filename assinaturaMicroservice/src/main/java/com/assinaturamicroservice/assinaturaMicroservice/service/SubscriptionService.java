package com.assinaturamicroservice.assinaturaMicroservice.service;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Subscription;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.SubscriptionDTO;
import com.assinaturamicroservice.assinaturaMicroservice.exceptions.SubscriptionCreationException;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.PlanRepository;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private PlanService planService;

    public void saveSubscription(Subscription subscription) {
        this.subscriptionRepository.save(subscription);
    }

    public Subscription createSubscription(SubscriptionDTO subscriptionDTO) {

        Plan plan = this.planService.getPlanByid(subscriptionDTO.plan().getId());
        Optional<Subscription> subscription = this.subscriptionRepository.findSubscriptionByUserId(subscriptionDTO.userId());
        if(subscription.isPresent() && subscription.get().getIsCanceled() == Boolean.FALSE){
            throw new SubscriptionCreationException.subscriptionAlreadyRegisterd();
        }
        subscriptionDTO.plan().setName(plan.getName());
        subscriptionDTO.plan().setDescription(plan.getDescription());
        Subscription newSubscription = new Subscription(subscriptionDTO);
        this.saveSubscription(newSubscription);
        return newSubscription;
    }

    public Subscription getSubcriptionById(Long id){
        Optional<Subscription> searchSubcription = this.subscriptionRepository.findBySubscriptionId(id);
        if (searchSubcription.isEmpty()){
            throw new NoSuchElementException("Assinatura");
        }
        return searchSubcription.get();
    }

    public Subscription canceledSubcription(Long subscriptionId) {
        Subscription subscription = getSubcriptionById(subscriptionId);
        subscription.setIsCanceled(Boolean.TRUE);
        return subscription;
    }

    public Subscription changeSubscriptionPlan(Long subscriptionId, Long planId){
        Subscription oldSubscription = getSubcriptionById(subscriptionId);
        Plan plan = this.planService.getPlanByid(planId);
        oldSubscription.setPlan(plan);
        return oldSubscription;
    }

}
