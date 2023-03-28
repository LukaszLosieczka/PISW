package com.example.order.controller;

import com.example.order.dto.CreateOrderDto;
import com.example.order.dto.OrderDto;
import com.example.order.model.Delivery;
import com.example.order.model.DeliveryStatus;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody CreateOrderDto createOrderDto){
        try {
            OrderDto result = orderService.createOrder(createOrderDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/delivery/{status}")
    public ResponseEntity<Object> changeDeliveryStatus(@PathVariable("id") Long orderId,
                                                       @PathVariable("status") DeliveryStatus status){
        try{
            Delivery delivery = orderService.changeDeliveryStatus(orderId, status);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
