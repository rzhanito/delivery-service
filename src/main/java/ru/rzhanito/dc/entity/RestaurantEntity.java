package ru.rzhanito.dc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE restaurant SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Имя не может быть null")
    @Size(min = 3, message = "Имя ресторана должно быть больше 3-х символов")
    @Size(max = 100, message = "Имя ресторана не может быть больше 100 символов")
    private String name;
    @NotBlank(message = "Локация не может быть null")
    @Size(min = 3, message = "Локация ресторана должна быть больше 3-х символов")
    @Size(max = 100, message = "Локация ресторана не может быть больше 100 символов")
    private String location;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<OrderEntity> currentOrders;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<OrderEntity> completedOrders;
    private boolean deleted = false;
}
