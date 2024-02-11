package asw.ordermanager.productservice.eventpublisher;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.productservice.api.event.ProductCreatedEvent;
import asw.ordermanager.productservice.api.event.ProductServiceEventChannel;
import asw.ordermanager.productservice.api.event.ProductStockLevelUpdatedEvent;
import asw.ordermanager.productservice.domain.ProductEventPublisher;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProductEventKafkaPublisher implements ProductEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    private String channelCreated = ProductServiceEventChannel.channel;

    private String channelUpdate = ProductServiceEventChannel.channelUpdate;

    @Override
    public void publish (DomainEvent event){
        if (event instanceof ProductCreatedEvent ev) {
            logger.info("EVENT PUBLISHER: " + ev.toString() + "ON CHANNEL: " + channelCreated);
            template.send(channelCreated, ev);
        }
        //logger.info("EVENT PUBLISHER" + event.toString() + "ON CHANNEL: " + channelUpdate);
        if (event instanceof ProductStockLevelUpdatedEvent ev) {
            logger.info("EVENT PUBLISHER: " + event.toString() + "ON CHANNEL: " + channelUpdate);
            template.send(channelUpdate, ev);
        }
    }
}
