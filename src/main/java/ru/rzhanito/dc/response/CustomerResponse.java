package ru.rzhanito.dc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rzhanito.dc.entity.CustomerEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String name;
    private String email;
    private List<OrderResponse> orderHistory;
    public static CustomerResponse toModel(CustomerEntity customer){
        return new CustomerResponse(customer.getName(), customer.getEmail(), customer.getOrderHistory().stream().map(OrderResponse::toModel).collect(Collectors.toList()));
    }
}
