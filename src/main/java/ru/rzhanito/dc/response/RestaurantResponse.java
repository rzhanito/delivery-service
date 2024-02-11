package ru.rzhanito.dc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rzhanito.dc.entity.RestaurantEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {
    private String name;
    private String location;
    private List<OrderResponse> currentOrders;
    private List<OrderResponse> completedOrders;

    public static RestaurantResponse toModel(RestaurantEntity restaurant){
        return new RestaurantResponse(restaurant.getName(), restaurant.getLocation(),
                restaurant
                    .getCurrentOrders()
                        .stream()
                        .map(OrderResponse::toModel)
                        .collect(Collectors.toList()),
                restaurant
                        .getCompletedOrders()
                            .stream()
                            .map(OrderResponse::toModel)
                            .collect(Collectors.toList()))
                ;
    }
}
