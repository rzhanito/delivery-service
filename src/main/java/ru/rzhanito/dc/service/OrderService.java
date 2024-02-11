package ru.rzhanito.dc.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
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
import ru.rzhanito.dc.response.OrderResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;

    public void createOrder(@Valid OrderEntity order){
        orderRepo.save(order);
    }

    public List<OrderResponse> getAllOrders() throws EntityNotFoundException {
        List<OrderEntity> allOrders = orderRepo.findAll();
        if(allOrders.isEmpty()) {
            throw new EntityNotFoundException("Заказов нет ваще ваще.");
        } else {
            return allOrders.stream().map(OrderResponse::toModel).collect(Collectors.toList());
        }
    }

    public List<OrderResponse> getOrdersFromCustomer(String customerName) throws EntityNotFoundException{
        Optional<CustomerEntity> existingCustomer = customerRepo.findByName(customerName);
        if(existingCustomer.isPresent()){
            List<OrderEntity> customerOrders = existingCustomer.get().getOrderHistory();
            if(customerOrders.isEmpty()){
                throw new EntityNotFoundException("У клиента " + customerName + " нет заказов.");
            }
            else{
                return customerOrders.stream().map(OrderResponse::toModel).collect(Collectors.toList());
            }
        }else {
            throw new EntityNotFoundException("Клиента " + customerName + " не существует.");
        }
    }


}

