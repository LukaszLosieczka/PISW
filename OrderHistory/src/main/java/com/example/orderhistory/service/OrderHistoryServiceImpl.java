package com.example.orderhistory.service;

import com.example.orderhistory.exception.OrderAlreadyExistsException;
import com.example.orderhistory.exception.OrderNotFoundException;
import com.example.orderhistory.model.DeliveryStatus;
import com.example.orderhistory.model.OrderHistory;
import com.example.orderhistory.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService{

    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public OrderHistory createOrderHistory(OrderHistory newOrder) {
        Optional<OrderHistory> order = orderHistoryRepository.findById(newOrder.getId());
        if(order.isPresent()){
            throw new OrderAlreadyExistsException(newOrder.getId());
        }
        return orderHistoryRepository.save(newOrder);
    }

    @Override
    public OrderHistory changeDeliveryStatus(Long orderHistoryId, DeliveryStatus deliveryStatus) {
        OrderHistory order = orderHistoryRepository.findById(orderHistoryId).orElseThrow(() -> new OrderNotFoundException(orderHistoryId));
        order.setDeliveryStatus(deliveryStatus);
        return orderHistoryRepository.save(order);
    }

    @Override
    public OrderHistory getOrderHistory(Long orderHistoryId) {
        return orderHistoryRepository.findById(orderHistoryId).orElseThrow(() -> new OrderNotFoundException(orderHistoryId));
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() {
        return orderHistoryRepository.findAll();
    }
}
