package asw.ordermanager.ordervalidationservice.eventlistener;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.orderservice.api.event.OrderServiceEventChannel;
import asw.ordermanager.ordervalidationservice.domain.OrderValidationServiceEventHandler;
import asw.ordermanager.productservice.api.event.ProductServiceEventChannel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EventListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    OrderValidationServiceEventHandler orderValidationServiceEventHandler;

    @KafkaListener(topics = {OrderServiceEventChannel.channel, ProductServiceEventChannel.channel, ProductServiceEventChannel.channelUpdate})
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        orderValidationServiceEventHandler.onEvent(record.value());

    }
}
