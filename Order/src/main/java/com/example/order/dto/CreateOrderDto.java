package com.example.order.dto;

import java.util.List;

public record CreateOrderDto(String customerName, List<CreateOrderItemDto> items) {
}
