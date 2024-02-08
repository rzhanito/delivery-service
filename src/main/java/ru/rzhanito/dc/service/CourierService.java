package ru.rzhanito.dc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rzhanito.dc.entity.CourierEntity;
import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
import ru.rzhanito.dc.exception.EntityNotFoundException;
import ru.rzhanito.dc.repo.CourierRepo;
import ru.rzhanito.dc.response.CourierResponse;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourierService {
    private final CourierRepo courierRepo;

    public void addCourier(CourierEntity courier) throws EntityAlreadyExistsException {
        Optional<CourierEntity> existingCourier = courierRepo.findByName(courier.getName());
        if(existingCourier.isPresent()){
            throw new EntityAlreadyExistsException("Курьер с таким именем уже существует.");
        }
        courierRepo.save(courier);
    }

    public CourierResponse setBusyStatus(String name) throws EntityNotFoundException{
        Optional<CourierEntity> existingCourier = courierRepo.findByName(name);
        if (existingCourier.isPresent()) {
            existingCourier.get().setIsBusy(!existingCourier.get().getIsBusy());
            courierRepo.save(existingCourier.get());
            return CourierResponse.toModel(existingCourier.get());
        }else {
            throw new EntityNotFoundException("Курьер с именем " + name + " не найден.");
        }
    }

    public CourierResponse getCourier(String name) throws EntityNotFoundException{
        Optional<CourierEntity> existingCourier = courierRepo.findByName(name);
        if(existingCourier.isPresent()){
            return CourierResponse.toModel(existingCourier.get());
        }else{
            throw new EntityNotFoundException("Курьер с именем " + name  + " не найден.");
        }
    }
}
