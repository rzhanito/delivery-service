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

import java.util.List;

@RestController
@RequestMapping("customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
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
}
