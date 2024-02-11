package ru.rzhanito.dc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.response.OrderResponse;
import ru.rzhanito.dc.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("create")
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderEntity order){
        orderService.createOrder(order);
        return ResponseEntity.ok("Заказ создан успешно.");
    }

    @GetMapping("get_all")
    public ResponseEntity<List<OrderResponse>> getAllOrders() throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("get")
    public ResponseEntity<List<OrderResponse>> getOrdersFromCustomer(@RequestParam String customerName) throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.getOrdersFromCustomer(customerName));
    }


}
