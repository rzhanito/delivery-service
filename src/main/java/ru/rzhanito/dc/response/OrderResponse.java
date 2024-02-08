package ru.rzhanito.dc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rzhanito.dc.entity.OrderEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String name;
    private String restaurantName;
    private String orderStatus;
    private String courierName;
    private String customerName;

    public static OrderResponse toModel(OrderEntity order){
        return new OrderResponse(order.getName(), order.getRestaurant().getName(), order.getStatus().name(), order.getCourier().getName(), order.getCustomer().getName());
    }
}
