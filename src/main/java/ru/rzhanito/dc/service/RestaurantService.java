package ru.rzhanito.dc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rzhanito.dc.entity.CourierEntity;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.entity.RestaurantEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.repo.CourierRepo;
import ru.rzhanito.dc.repo.OrderRepo;
import ru.rzhanito.dc.repo.RestaurantRepo;
import ru.rzhanito.dc.response.OrderStatus;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final CourierRepo courierRepo;
    private final OrderRepo orderRepo;

    public void addRestaurant(RestaurantEntity restaurant) throws EntityAlreadyExistsException {
        Optional<RestaurantEntity> existingRestaurant = restaurantRepo.findByName(restaurant.getName());
        if(existingRestaurant.isPresent()){
            throw new EntityAlreadyExistsException("Ресторан " + restaurant.getName() + " уже существует.");
        }else{
            restaurantRepo.save(restaurant);
        }
    }

//    public OrderEntity takeOrder(OrderEntity order) throws EntityNotFoundException {
//        Optional<CourierEntity> availableCourier = courierRepo.findFirstByIsBusyFalse();
//        if(availableCourier.isPresent()){
//            availableCourier.get().getOrders().add(order);
//            order.setStatus(OrderStatus.IN_PROCESS);
//            order.setCourier(availableCourier.get());
//            return order;
//        }else{
//            order.setStatus(OrderStatus.REGISTERED);
//            return order;
//        }
//    }
}
