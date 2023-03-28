package com.example.order.dto;


import com.example.order.model.DeliveryStatus;

import java.math.BigDecimal;

public record OrderHistoryDto(Long id, String customerName, String courierName, DeliveryStatus deliveryStatus,
                              String productsNames, BigDecimal totalPrice) {
}
