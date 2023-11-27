package com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura;

import com.assinaturamicroservice.assinaturaMicroservice.dtos.PlanDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="plans")
@Table(name="plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 128)
    private String name;

    @Column(length = 4096)
    private String description;

    @Column(length = 10)
    private Boolean isDeleted = Boolean.FALSE;

    public Plan(PlanDTO planDTO){
        this.name = planDTO.name();
        this.description = planDTO.description();
    }
}
