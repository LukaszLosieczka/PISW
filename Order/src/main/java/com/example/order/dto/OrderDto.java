package com.example.order.dto;

import com.example.order.model.DeliveryStatus;

import java.util.List;

public record OrderDto(Long orderId, String customerName, List<OrderItemDto> items, String courierName, DeliveryStatus status) {
}
