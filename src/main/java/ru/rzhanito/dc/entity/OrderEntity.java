package ru.rzhanito.dc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import ru.rzhanito.dc.response.OrderStatus;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id = ?")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Название заказа не может быть null")
    @Size(min = 3, message = "Название заказа должно быть больше 3-х символов")
    @Size(max = 100, message = "Название заказа не может быть больше 100 символов")
    private String name;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Статус заказа не может быть null")
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "deleted")
    private Boolean deleted = false;
}
