package com.example.orderhistory.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_history")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderHistory {

    @Id
    private Long id;

    private String customerName;

    private String courierName;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private String productsNames;

    private BigDecimal totalPrice;

    public OrderHistory(Long id, String customerName, String courierName, DeliveryStatus deliveryStatus, String productsNames, BigDecimal totalPrice){
        this.id = id;
        this.customerName = customerName;
        this.courierName = courierName;
        this.deliveryStatus = deliveryStatus;
        this.productsNames = productsNames;
        this.totalPrice = totalPrice;
    }
}
