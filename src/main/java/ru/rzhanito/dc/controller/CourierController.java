package ru.rzhanito.dc.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rzhanito.dc.entity.CourierEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.response.CourierResponse;
import ru.rzhanito.dc.service.CourierService;

@RestController
@RequestMapping("courier")
@AllArgsConstructor
public class CourierController {
    private final CourierService courierService;
    @PostMapping("add")
    public ResponseEntity<String> addCourier(@RequestBody CourierEntity courier) throws EntityAlreadyExistsException {
        courierService.addCourier(courier);
        return ResponseEntity.ok("Курьер добавлен успешно.");
    }

    @PatchMapping("setBusyStatus")
    public ResponseEntity<CourierResponse> setBusyStatus(@RequestParam String name) throws EntityNotFoundException {
        return ResponseEntity.ok(courierService.setBusyStatus(name));
    }

    @GetMapping("get")
    public ResponseEntity<CourierResponse> getCourier(@RequestParam String name) throws EntityNotFoundException{
        return ResponseEntity.ok(courierService.getCourier(name));
    }
}
