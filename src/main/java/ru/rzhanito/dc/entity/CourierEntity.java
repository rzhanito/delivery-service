package ru.rzhanito.dc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть null")
    @Size(min = 3, message = "Имя курьера должно быть больше 3-х символов")
    @Size(max = 100, message = "Имя курьера не может быть больше 100 символов")
    private String name;
    @NotNull(message = "Статус занятости курьера не может быть null")
    private Boolean isBusy;
    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
}
