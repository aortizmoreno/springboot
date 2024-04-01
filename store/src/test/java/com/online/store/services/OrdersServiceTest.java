package com.online.store.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.online.store.models.Order;
import com.online.store.repositories.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

    OrdersService ordersService;

    @Mock
    OrderRepository orderRepository;

    List<Order> orders = new ArrayList<Order>();
    
    @BeforeEach
    public void init() {
        ordersService = new OrdersService(orderRepository, 1);
        Order order = new Order("", "", "", null, 0, null, null);
        orders.add(order);
        when(orderRepository.saveAll(orders)).thenReturn(orders);
    }

    @Test
    void testPlaceOrders() {
       ordersService.placeOrders(orders);
        verify(orderRepository, times(1)).saveAll(orders);
        System.err.println("test");
    }
}
