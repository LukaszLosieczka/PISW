package com.example.order.service;

import com.example.order.dto.CreateOrderDto;
import com.example.order.dto.OrderDto;
import com.example.order.model.Delivery;
import com.example.order.model.DeliveryStatus;

public interface OrderService {

    OrderDto createOrder(CreateOrderDto createOrderDto);

    Delivery changeDeliveryStatus(Long orderId, DeliveryStatus deliveryStatus);
}
