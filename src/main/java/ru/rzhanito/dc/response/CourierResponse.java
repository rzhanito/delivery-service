package ru.rzhanito.dc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rzhanito.dc.entity.CourierEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierResponse {
    private String name;
    private boolean isBusy;
    private List<OrderResponse> orders;

    public static CourierResponse toModel(CourierEntity courier){
        return new CourierResponse(courier.getName(), courier.getIsBusy(), courier.getOrders().stream().map(OrderResponse::toModel).collect(Collectors.toList()));
    }
}
