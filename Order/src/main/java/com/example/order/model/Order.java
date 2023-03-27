package com.example.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nullable
    private Long id;

    private String customerName;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @OneToOne
    private Delivery delivery;

    public Order(String customerName, List<OrderItem> items, Delivery delivery){
        this.customerName = customerName;
        this.items = items;
        this.delivery = delivery;
    }
}
