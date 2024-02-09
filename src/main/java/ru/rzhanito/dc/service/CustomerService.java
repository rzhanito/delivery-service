package ru.rzhanito.dc.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rzhanito.dc.entity.CustomerEntity;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.repo.CustomerRepo;
import ru.rzhanito.dc.response.CustomerResponse;
import ru.rzhanito.dc.response.OrderResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final RestaurantService restaurantService;

    public CustomerResponse getCustomer(String name) throws EntityNotFoundException {
        Optional<CustomerEntity> existingCustomer = customerRepo.findByName(name);
        if(existingCustomer.isPresent()){
            return CustomerResponse.toModel(existingCustomer.get());
        }else{
            throw new EntityNotFoundException("Пользователь " + name + " не найден.");
        }
    }

    public List<CustomerResponse> getAllCustomers() throws EntityNotFoundException {
        List<CustomerEntity> customers = customerRepo.findAll();
        if(customers.isEmpty()) throw new EntityNotFoundException("Пользователей нет.");
        else {
            return customers.stream().map(CustomerResponse::toModel).collect(Collectors.toList());
        }
    }

    public void addCustomer(CustomerEntity customer) throws EntityAlreadyExistsException {
        Optional<CustomerEntity> existingCustomer = customerRepo.findByName(customer.getName());
        if(existingCustomer.isPresent()){
            throw new EntityAlreadyExistsException("Пользователь " + customer.getName() + " уже существует.");
        } else{
            customerRepo.save(customer);
        }
    }

    public void makeOrder(OrderEntity order){

    }
}
