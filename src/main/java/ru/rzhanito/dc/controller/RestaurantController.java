package ru.rzhanito.dc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.entity.RestaurantEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.response.OrderResponse;
import ru.rzhanito.dc.response.RestaurantResponse;
import ru.rzhanito.dc.service.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;


    @PostMapping("add")
    public ResponseEntity<String> addRestaurant(@RequestBody @Valid RestaurantEntity restaurant) throws EntityAlreadyExistsException {
        restaurantService.addRestaurant(restaurant);
        return ResponseEntity.ok("Ресторан " + restaurant.getName() + " добавлен успешно.");
    }

    @GetMapping("get_all")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants() throws EntityNotFoundException {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("get")
    public ResponseEntity<RestaurantResponse> getRestaurantByName(@RequestParam String restaurantName) throws EntityNotFoundException {
        return ResponseEntity.ok(restaurantService.getRestaurantByName(restaurantName));
    }

    @GetMapping("get_by_status")
    public ResponseEntity<List<OrderResponse>> getOrdersInRestaurantByStatus(@RequestParam String restaurantName, @RequestParam String orderStatus) throws EntityNotFoundException {
        return ResponseEntity.ok(restaurantService.getOrdersInRestaurantByStatus(restaurantName, orderStatus));
    }
}
