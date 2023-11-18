package com.assinaturamicroservice.assinaturaMicroservice.service;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Subscription;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.UserDTO;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.PlanRepository;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlanRepository planRepository;

    public void saveUser(Subscription subscription) {
        this.userRepository.save(subscription);
    }

    public Subscription createUser(UserDTO userDTO) {

        Plan plan = this.planRepository.getById(userDTO.plan().getId());

        if (plan == null) {
            throw new NoSuchElementException("plano");
        }
        userDTO.plan().setName(plan.getName());
        userDTO.plan().setDescription(plan.getDescription());
        Subscription newSubscription = new Subscription(userDTO);
        this.saveUser(newSubscription);
        return newSubscription;

    }


    public Subscription canceledUserPlan(Long id) {
        Optional<Subscription> userOptional = this.userRepository.findByUserId(id);
        if (userOptional.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        Subscription subscription = userOptional.get();
        subscription.setPlan(null);
        return subscription;
    }

}
