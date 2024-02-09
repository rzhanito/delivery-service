package ru.rzhanito.dc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("add")
    public ResponseEntity<String> addOrder(@Valid @RequestBody OrderEntity order) throws EntityAlreadyExistsException {
        orderService.addOrder(order);
        return ResponseEntity.ok("Заказ добавлен успешно.");
    }
}
