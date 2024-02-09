package ru.rzhanito.dc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rzhanito.dc.entity.RestaurantEntity;

import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByName(String name);
}
