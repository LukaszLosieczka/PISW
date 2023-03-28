package com.example.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@NoArgsConstructor
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nullable
    private Long id;

    private String courierName;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(String courierName, DeliveryStatus status){
        this.courierName = courierName;
        this.status = status;
    }
}
