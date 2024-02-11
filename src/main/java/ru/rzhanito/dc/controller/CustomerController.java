package ru.rzhanito.dc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rzhanito.dc.entity.CustomerEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.response.CustomerResponse;
import ru.rzhanito.dc.service.CustomerService;
import ru.rzhanito.dc.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    @GetMapping("get")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestParam String name) throws EntityNotFoundException {
        return ResponseEntity.ok(customerService.getCustomer(name));
    }

    @PostMapping("add")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerEntity customer) throws EntityAlreadyExistsException {
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Пользователь " + customer.getName() + " успешно добавлен.");
    }

    @GetMapping("get_all")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() throws EntityNotFoundException{
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PatchMapping("change_customer")
    public ResponseEntity<CustomerResponse> changeCustomerPartially(
            @RequestParam String name,
            @RequestParam(required = false) String newName,
            @RequestParam(required = false) String newEmail)
                throws EntityNotFoundException
    {
        return ResponseEntity.ok(customerService.changeCustomerPartially(name, newName, newEmail));
    }

    @PostMapping("make_order")
    public ResponseEntity<String> makeOrder(
            @RequestParam String orderName,
            @RequestParam String restaurantName,
            @RequestParam String customerName)
                throws EntityNotFoundException
    {
        restaurantService.takeOrder(orderName, restaurantName, customerName);
        return ResponseEntity.ok("Заказ '" + orderName + "'  в ресторане '" + restaurantName + "' на имя '" + customerName + "' создан успешно");
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteCustomerByName(String name) throws EntityNotFoundException {
        customerService.deleteCustomerByName(name);
        return ResponseEntity.ok("Клиент " + name + " удален успешно.");
    }
}
