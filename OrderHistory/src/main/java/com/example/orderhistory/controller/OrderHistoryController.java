package com.example.orderhistory.controller;

import com.example.orderhistory.model.DeliveryStatus;
import com.example.orderhistory.model.OrderHistory;
import com.example.orderhistory.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/ordersHistory")
@RequiredArgsConstructor
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrderHistory(@RequestBody OrderHistory orderHistory){
        try{
            OrderHistory newOrderHistory = orderHistoryService.createOrderHistory(orderHistory);
            return new ResponseEntity<>(newOrderHistory, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/delivery/{status}")
    public ResponseEntity<Object> changeDeliveryStatus(@PathVariable("id") Long orderId,
                                                       @PathVariable("status") DeliveryStatus status){
        try{
            OrderHistory orderHistory = orderHistoryService.changeDeliveryStatus(orderId, status);
            return new ResponseEntity<>(orderHistory, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderHistory(@PathVariable("id") Long orderHistoryId){
        try{
            OrderHistory orderHistory = orderHistoryService.getOrderHistory(orderHistoryId);
            return new ResponseEntity<>(orderHistory, HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAllOrderHistory(){
        return new ResponseEntity<>(orderHistoryService.getAllOrderHistory(), HttpStatus.OK);
    }
}
