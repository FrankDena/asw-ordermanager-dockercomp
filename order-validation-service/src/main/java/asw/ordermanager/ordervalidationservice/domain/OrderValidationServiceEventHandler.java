package asw.ordermanager.ordervalidationservice.domain;


import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import asw.ordermanager.productservice.api.event.ProductCreatedEvent;
import asw.ordermanager.productservice.api.event.ProductStockLevelUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EventListener;
import java.util.logging.Logger;

@Service
public class OrderValidationServiceEventHandler {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private  OrderService orderService;
    @Autowired
    private ProductService productService;


    public void onEvent(DomainEvent event) {

        if (event instanceof OrderCreatedEvent ev) {
            logger.info("PROCESSING ORDER CREATED EVENT: " + event);
            updateOrders(ev);
        }
        if (event instanceof ProductCreatedEvent ev) {
            logger.info("PROCESSING PRODUCT CREATED EVENT: " + event);
            updateProducts(ev);
        }
        if (event instanceof ProductStockLevelUpdatedEvent ev) {
            logger.info("PROCESSING STOCK LEVEL UPDATE EVENT: " + event);
            updateProductStockLevel(ev);
        }
    }

    private void updateProductStockLevel(ProductStockLevelUpdatedEvent event) {
        productService.updateProductStockLevel(event.getName(), event.getStockLevel());
    }

    private void updateProducts(ProductCreatedEvent event) {
        productService.createProduct(event.getName(), event.getStockLevel());
    }

    private void updateOrders(OrderCreatedEvent event) {
        orderService.updateOrders(event.getCustomer(), event.getOrderItems());
    }

}
