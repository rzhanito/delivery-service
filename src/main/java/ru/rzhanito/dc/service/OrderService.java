package ru.rzhanito.dc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.repo.OrderRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final RestaurantService restaurantService;

//    public void addOrder(OrderEntity order) throws EntityAlreadyExistsException {
//        OrderEntity orderEntity = restaurantService.takeOrder()
//    }
}
