package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.orderservice.api.rest.OrderItemElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private OrderRepository orderRepository;

    public Order updateOrders(String customer, List<OrderItemElement> orderItems) {
        logger.info("UPDATE ORDERS BY ORDER VALIDATION SERVICE");
        Order order = new Order(customer, toOrderItems(orderItems));
        order = orderRepository.save(order);
        return order;
    }

    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return order;
    }


    //LI INSERISCO QUA PER ORA. DA MODIFICARE
    private OrderItem toOrderItem(OrderItemElement item) {
        return new OrderItem(
                item.getProduct(),
                item.getQuantity());
    }
    private List<OrderItem> toOrderItems(List<OrderItemElement> items) {
        List<OrderItem> orderItems =
                items
                        .stream()
                        .map(item -> toOrderItem(item))
                        .collect(Collectors.toList());
        return orderItems;
    }


}
