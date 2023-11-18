package com.assinaturamicroservice.assinaturaMicroservice.domain.assinatura;
import com.assinaturamicroservice.assinaturaMicroservice.dtos.SubscriptionDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name="subscription")
@Table(name="subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long subscriptionId;

    @Column()
    private String userId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column()
    private Boolean isCanceled = Boolean.FALSE;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    public Subscription(SubscriptionDTO subscriptionDTO){
        this.userId = subscriptionDTO.userId();
        this.plan = subscriptionDTO.plan();
    }

}
