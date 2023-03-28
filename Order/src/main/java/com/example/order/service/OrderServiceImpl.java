package com.example.order.service;

import com.example.order.dto.CreateOrderDto;
import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderHistoryDto;
import com.example.order.dto.OrderItemDto;
import com.example.order.exception.IncorrectOrderException;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.exception.ProductNotFoundException;
import com.example.order.model.*;
import com.example.order.repository.DeliveryRepository;
import com.example.order.repository.OrderItemRepository;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final DeliveryRepository deliveryRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    private static final String courierName = "DHL";

    private static final String orderHistoryURL = "http://localhost:8081/ordersHistory";

    public OrderServiceImpl(DeliveryRepository deliveryRepository, OrderItemRepository orderItemRepository, OrderRepository orderRepository,
                            ProductRepository productRepository, RestTemplateBuilder restTemplateBuilder){
        this.deliveryRepository = deliveryRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

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
        // persist
        Delivery delivery = deliveryRepository.save(new Delivery(courierName, DeliveryStatus.CREATED));
        Order newOrder = new Order(createOrderDto.customerName(), newOrderItems, delivery);
        newOrderItems.forEach(orderItem -> orderItem.setOrder(newOrder));
        Order order = orderRepository.save(newOrder);
        orderItemRepository.saveAll(newOrderItems);
        // connect to orderHistory
        HttpEntity<OrderHistoryDto> request = new HttpEntity<>(toOrderHistoryDto(order));
        ResponseEntity<OrderHistoryDto> response = restTemplate.exchange(orderHistoryURL + "/create", HttpMethod.POST, request, OrderHistoryDto.class);
        if(response.getStatusCode() != HttpStatus.CREATED){
            log.error("Problem occurred while creating order history");
        }
        // map
        List<OrderItemDto> orderItemDtos = order.getItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem.getProduct().getName(), orderItem.getQuantity(), orderItem.getProduct().getPrice())).toList();
        return new OrderDto(order.getId(), order.getCustomerName(), orderItemDtos, delivery.getCourierName(), delivery.getStatus());
    }

    @Override
    public Delivery changeDeliveryStatus(Long orderId, DeliveryStatus deliveryStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        Delivery delivery = order.getDelivery();
        delivery.setStatus(deliveryStatus);
        // connect to orderHistory
        HttpEntity<Object> request = new HttpEntity<>(null);
        ResponseEntity<OrderHistoryDto> response = restTemplate.exchange(orderHistoryURL + "/" + order.getId() + "/delivery/" + deliveryStatus,
                HttpMethod.PUT, request, OrderHistoryDto.class);
        if(response.getStatusCode() != HttpStatus.OK){
            log.error("Problem occurred while changing delivery status in order history");
        }
        return deliveryRepository.save(delivery);
    }

    private OrderHistoryDto toOrderHistoryDto(Order order){
        String productsNames = order.getItems().stream()
                .map(item -> item.getProduct().getName())
                .collect(Collectors.joining(", "));
        BigDecimal totalPrice = order.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new OrderHistoryDto(order.getId(), order.getCustomerName(), order.getDelivery().getCourierName(),
                order.getDelivery().getStatus(),productsNames, totalPrice);
    }
}
