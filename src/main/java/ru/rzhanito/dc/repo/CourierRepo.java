package ru.rzhanito.dc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rzhanito.dc.entity.CourierEntity;

import java.util.Optional;

@Repository
public interface CourierRepo extends JpaRepository<CourierEntity, Long> {
    Optional<CourierEntity> findByName(String name);
}
