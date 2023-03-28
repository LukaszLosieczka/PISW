package com.example.orderhistory.service;

import com.example.orderhistory.model.DeliveryStatus;
import com.example.orderhistory.model.OrderHistory;

import java.util.List;

public interface OrderHistoryService {

    OrderHistory createOrderHistory(OrderHistory newOrder);

    OrderHistory changeDeliveryStatus(Long orderHistoryId, DeliveryStatus deliveryStatus);

    OrderHistory getOrderHistory(Long orderHistoryId);

    List<OrderHistory> getAllOrderHistory();
}
