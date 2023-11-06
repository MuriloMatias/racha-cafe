package com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String email;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public User(UserDTO userDTO){
        this.name = userDTO.name();
        this.email = userDTO.email();
        this.plan = userDTO.plan();
    }

}
