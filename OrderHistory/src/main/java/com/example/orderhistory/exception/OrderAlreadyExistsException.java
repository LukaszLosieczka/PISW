package com.example.orderhistory.exception;

public class OrderAlreadyExistsException extends RuntimeException{
    public OrderAlreadyExistsException(Long orderId){
        super("Order with id: " + orderId + " already exists");
    }
}
