package ru.rzhanito.dc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rzhanito.dc.entity.OrderEntity;
import ru.rzhanito.dc.response.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByName(String name);
    List<OrderEntity> findAllByStatus(OrderStatus status);
}
