package ru.rzhanito.dc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rzhanito.dc.entity.RestaurantEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.service.RestaurantService;

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
}
