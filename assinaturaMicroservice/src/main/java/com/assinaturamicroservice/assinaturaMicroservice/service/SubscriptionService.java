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
        if(subscription.isPresent()){
            throw new SubscriptionCreationException.subscriptionAlreadyRegisterd();
        }
        subscriptionDTO.plan().setName(plan.getName());
        subscriptionDTO.plan().setDescription(plan.getDescription());
        Subscription newSubscription = new Subscription(subscriptionDTO);
        this.saveSubscription(newSubscription);
        return newSubscription;
    }

    public Subscription getPlan(Long id){

    }


//    public Subscription canceledUserPlan(Long id) {
//        Optional<Subscription> userOptional = this.subscriptionRepository.findByUserId(id);
//        if (userOptional.isEmpty()) {
//            throw new NoSuchElementException("User not found");
//        }
//        Subscription subscription = userOptional.get();
//        subscription.setPlan(null);
//        return subscription;
//    }

}
