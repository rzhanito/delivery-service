package ru.rzhanito.dc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rzhanito.dc.entity.CourierEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.response.CourierResponse;
import ru.rzhanito.dc.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("courier")
@AllArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @PostMapping("add")
    public ResponseEntity<String> addCourier(@Valid @RequestBody CourierEntity courier) throws EntityAlreadyExistsException {
        courierService.addCourier(courier);
        return ResponseEntity.ok("Курьер добавлен успешно.");
    }

    @PatchMapping("setBusyStatus")
    public ResponseEntity<CourierResponse> setBusyStatus(@RequestParam String name) throws EntityNotFoundException {
        return ResponseEntity.ok(courierService.setBusyStatus(name));
    }

    @GetMapping("get")
    public ResponseEntity<CourierResponse> getCourier(@RequestParam String name) throws EntityNotFoundException {
        return ResponseEntity.ok(courierService.getCourier(name));
    }

    @GetMapping("get_all")
    public List<CourierResponse> getAllCouriers() throws EntityNotFoundException {
        return courierService.getAllCouriers();
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteCourier(@RequestParam String name) throws EntityNotFoundException {
        courierService.deleteCourier(name);
        return ResponseEntity.ok("Курьер с именем " + name + " удален успешно.");
    }


}
