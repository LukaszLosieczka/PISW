package com.example.orderhistory.service;

import com.example.orderhistory.exception.OrderAlreadyExistsException;
import com.example.orderhistory.exception.OrderNotFoundException;
import com.example.orderhistory.model.DeliveryStatus;
import com.example.orderhistory.model.OrderHistory;
import com.example.orderhistory.repository.OrderHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderHistoryServiceImplTest {

    @Mock
    private OrderHistoryRepository repositoryMock;

    @InjectMocks
    private OrderHistoryServiceImpl orderHistoryServiceUnderTest;


    @Test
    void shouldReturnOrderHistory_whenCreatingOrder() {
        //given
        OrderHistory orderHistory = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        when(repositoryMock.save(eq(orderHistory))).thenReturn(orderHistory);
        //when
        OrderHistory result = orderHistoryServiceUnderTest.createOrderHistory(orderHistory);
        //then
        assertThat(result).isEqualTo(orderHistory);
    }

    @Test
    void shouldThrowOrderAlreadyExistsException_whenCreatingOrderWithAlreadyExistingId() {
        //given
        OrderHistory orderHistory = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        when(repositoryMock.findById(eq(orderHistory.getId()))).thenReturn(Optional.of(orderHistory));
        //when then
        assertThatThrownBy(() -> orderHistoryServiceUnderTest.createOrderHistory(orderHistory))
                .isInstanceOf(OrderAlreadyExistsException.class);
    }

    @Test
    void shouldReturnOrderHistoryWithChangedDeliveryStatus_whenChangingDeliveryStatus() {
        //given
        OrderHistory orderHistory = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        DeliveryStatus newDeliveryStatus = DeliveryStatus.PICKED_UP;
        OrderHistory expectedOrderHistory = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.PICKED_UP,
                "pepsi, lays", new BigDecimal("50.20"));
        when(repositoryMock.findById(eq(orderHistory.getId()))).thenReturn(Optional.of(orderHistory));
        when(repositoryMock.save(any(OrderHistory.class))).thenAnswer(i -> i.getArguments()[0]);
        //when
        OrderHistory result = orderHistoryServiceUnderTest.changeDeliveryStatus(orderHistory.getId(), newDeliveryStatus);
        //then
        assertThat(result).isEqualTo(expectedOrderHistory);
    }

    @Test
    void shouldThrowOrderNotFoundException_whenChangingDeliveryStatusForNonExistingOrderHistory() {
        //given
        OrderHistory orderHistory = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        DeliveryStatus newDeliveryStatus = DeliveryStatus.PICKED_UP;
        when(repositoryMock.findById(eq(orderHistory.getId()))).thenReturn(Optional.empty());
        //when then
        assertThatThrownBy(() -> orderHistoryServiceUnderTest.changeDeliveryStatus(orderHistory.getId(), newDeliveryStatus))
                .isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    void shouldReturnOrderHistory_whenGetOrderHistoryById() {
        //given
        OrderHistory orderHistory = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        when(repositoryMock.findById(eq(orderHistory.getId()))).thenReturn(Optional.of(orderHistory));
        //when
        OrderHistory result = orderHistoryServiceUnderTest.getOrderHistory(orderHistory.getId());
        //then
        assertThat(result).isEqualTo(orderHistory);
    }

    @Test
    void shouldThrowOrderNotFoundException_whenGetOrderHistoryByNotExistingId() {
        //given
        OrderHistory orderHistory = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        when(repositoryMock.findById(eq(orderHistory.getId()))).thenReturn(Optional.empty());
        //when then
        assertThatThrownBy(() -> orderHistoryServiceUnderTest.getOrderHistory(orderHistory.getId()))
                .isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    void shouldReturnListOfOrderHistory_whenGetAllOrderHistory() {
        //given
        OrderHistory orderHistory1 = new OrderHistory(1L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        OrderHistory orderHistory2 = new OrderHistory(2L, "customer1", "DHL", DeliveryStatus.CREATED,
                "pepsi, lays", new BigDecimal("50.20"));
        List<OrderHistory> expectedResult = List.of(orderHistory1, orderHistory2);
        when(repositoryMock.findAll()).thenReturn(expectedResult);
        //when
        List<OrderHistory> result = orderHistoryServiceUnderTest.getAllOrderHistory();
        //then
        assertThat(result).isEqualTo(expectedResult);
    }
}