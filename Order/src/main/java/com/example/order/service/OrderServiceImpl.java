package com.example.order.service;

import com.example.order.dto.CreateOrderDto;
import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderItemDto;
import com.example.order.exception.IncorrectOrderException;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.exception.ProductNotFoundException;
import com.example.order.model.*;
import com.example.order.repository.DeliveryRepository;
import com.example.order.repository.OrderItemRepository;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final DeliveryRepository deliveryRepository;

    private final OrderItemRepository orderItemRepository;

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private static final String courierName = "DHL";

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        List<OrderItem> newOrderItems = createOrderDto.items()
                .stream()
                .filter(item -> item.quantity() > 0)
                .map(item -> {
                    Product product = productRepository.findById(item.productId())
                            .orElseThrow(() -> new ProductNotFoundException(item.productId()));
                    return new OrderItem(product, item.quantity());
                }).toList();
        if(newOrderItems.isEmpty()){
            throw new IncorrectOrderException();
        }
        Delivery delivery = deliveryRepository.save(new Delivery(courierName, DeliveryStatus.CREATED));
        Order newOrder = new Order(createOrderDto.customerName(), newOrderItems, delivery);
        newOrderItems.forEach(orderItem -> orderItem.setOrder(newOrder));
        Order order = orderRepository.save(newOrder);
        orderItemRepository.saveAll(newOrderItems);
        List<OrderItemDto> orderItemDtos = order.getItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem.getProduct().getName(), orderItem.getQuantity(), orderItem.getProduct().getPrice())).toList();
        return new OrderDto(order.getId(), order.getCustomerName(), orderItemDtos, delivery.getCourierName(), delivery.getStatus());
    }

    @Override
    public Delivery changeDeliveryStatus(Long orderId, DeliveryStatus deliveryStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        Delivery delivery = order.getDelivery();
        delivery.setStatus(deliveryStatus);
        return deliveryRepository.save(delivery);
    }
}
