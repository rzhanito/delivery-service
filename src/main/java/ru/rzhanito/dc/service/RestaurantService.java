package ru.rzhanito.dc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rzhanito.dc.entity.CourierEntity;
import ru.rzhanito.dc.entity.CustomerEntity;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.entity.RestaurantEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.repo.CourierRepo;
import ru.rzhanito.dc.repo.CustomerRepo;
import ru.rzhanito.dc.repo.OrderRepo;
import ru.rzhanito.dc.repo.RestaurantRepo;
import ru.rzhanito.dc.response.OrderResponse;
import ru.rzhanito.dc.response.OrderStatus;
import ru.rzhanito.dc.response.RestaurantResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final CourierRepo courierRepo;
    private final CustomerRepo customerRepo;
    private final OrderRepo orderRepo;

    public void addRestaurant(RestaurantEntity restaurant) throws EntityAlreadyExistsException {
        Optional<RestaurantEntity> existingRestaurant = restaurantRepo.findByName(restaurant.getName());
        if(existingRestaurant.isPresent()){
            throw new EntityAlreadyExistsException("Ресторан " + restaurant.getName() + " уже существует.");
        }else{
            restaurantRepo.save(restaurant);
        }
    }

    public List<RestaurantResponse> getAllRestaurants() throws EntityNotFoundException {
        List<RestaurantEntity> allRestaurants = restaurantRepo.findAll();
        if(allRestaurants.isEmpty()) throw new EntityNotFoundException("Ресторанов нет.");
        else return allRestaurants.stream().map(RestaurantResponse::toModel).collect(Collectors.toList());
    }

    public RestaurantResponse getRestaurantByName(String restaurantName) throws EntityNotFoundException{
        Optional<RestaurantEntity> existingRestaurant = restaurantRepo.findByName(restaurantName);
        if(existingRestaurant.isPresent()) return RestaurantResponse.toModel(existingRestaurant.get());
        else throw new EntityNotFoundException("Ресторан " + restaurantName + " не найден.");
    }

    public void takeOrder(String orderName, String restaurantName, String customerName) throws EntityNotFoundException {
        Optional<RestaurantEntity> existingRestaurant = restaurantRepo.findByName(restaurantName);
        if(existingRestaurant.isPresent()){
            Optional<CustomerEntity> existingCustomer = customerRepo.findByName(customerName);
            if(existingCustomer.isPresent()){
                OrderEntity order = new OrderEntity();
                order.setName(orderName);
                order.setCustomer(existingCustomer.get());
                order.setRestaurant(existingRestaurant.get());
                order.setStatus(OrderStatus.REGISTERED);
                Optional<CourierEntity> availableCourier = courierRepo.findFirstByIsBusyFalse();
                if(availableCourier.isPresent()) {
                    order.setCourier(availableCourier.get());
                    order.setStatus(OrderStatus.IN_PROCESS);
                }
                orderRepo.save(order);
                existingCustomer.get().getOrderHistory().add(order);
                existingRestaurant.get().getCurrentOrders().add(order);
            }
            else {
                throw new EntityNotFoundException("Клиента с именем " + customerName + " не существует.");
            }
        }else {
            throw new EntityNotFoundException("Ресторана с названием " + restaurantName + " не существует.");
        }
    }

    public List<OrderResponse> getOrdersInRestaurantByStatus(String restaurantName, String orderStatus) throws EntityNotFoundException {
        Optional<RestaurantEntity> existingRestaurant = restaurantRepo.findByName(restaurantName);
        if(existingRestaurant.isPresent()){
            List<OrderEntity> ordersByStatus = orderRepo.findAllByStatus(OrderStatus.valueOf(orderStatus));
            if(ordersByStatus.isEmpty()){
                throw new EntityNotFoundException("Заказов со статусом " + orderStatus + " нет.");
            }else{
                return ordersByStatus.stream().map(OrderResponse::toModel).collect(Collectors.toList());
            }
        }else{
            throw new EntityNotFoundException("Ресторана " + restaurantName + " не существует.");
        }
    }
}
