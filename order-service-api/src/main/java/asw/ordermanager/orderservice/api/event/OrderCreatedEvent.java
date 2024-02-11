package asw.ordermanager.orderservice.api.event;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.orderservice.api.rest.OrderItemElement;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderCreatedEvent implements DomainEvent {

    private String customer;
    private String address;
    private List<OrderItemElement> orderItems;
    private double total;

}
