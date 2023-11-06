package com.assinaturamicroservice.assinaturaMicroservice.service;

import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.Plan;
import com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura.User;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.UserDTO;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.PlanRepository;
import com.assinaturamicroservice.assinaturaMicroservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlanRepository planRepository;

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        Plan plan = this.planRepository.getById(userDTO.plan().getId());
        if (plan == null) {
            throw new NoSuchElementException("plano");
        }
        userDTO.plan().setName(plan.getName());
        userDTO.plan().setDescription(plan.getDescription());
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;

    }
}
