package ru.almaz.OrderEventSourcing.models.material;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;
import ru.almaz.OrderEventSourcing.events.order.*;
import ru.almaz.OrderEventSourcing.models.db.OrderEvent;

import java.util.List;

@Data
public class Order {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private List<OrderEvent> events;

    private Long id;
    private Long clientId;
    private Long productId;
    private Integer productPrice;
    private String status;

    public void applyJsonAll(List<OrderEvent> events) {
        this.events = events;
        for (OrderEvent event : this.events) {
            applyJson(event);
        }
    }

    public void apply(OrderCreatedEvent event)  {
        this.id = event.getOrderId();
        this.clientId = event.getClientId();
        this.productId = event.getProductId();
        this.productPrice = event.getProductPrice();
        this.status = "Registered";
    }

    public void apply(OrderCanceledEvent event) {
        this.status = "Canceled";
    }

    public void apply(OrderAcceptEvent event) {
        this.status = "Accepted";
    }

    public void apply(OrderReadyEvent event) {
        this.status = "Ready";
    }

    public void apply(OrderGiveEvent event) {
        this.status = "Given";
    }

    public void applyJson(OrderEvent event) {
        if (event.getType().equals(OrderCreatedEvent.class.getName())) {
            OrderCreatedEvent createdEvent = fromJson(event.getData(), OrderCreatedEvent.class);
            apply(createdEvent);
        } else if (event.getType().equals(OrderAcceptEvent.class.getName())) {
            OrderAcceptEvent acceptEvent = fromJson(event.getData(), OrderAcceptEvent.class);
            apply(acceptEvent);
        } else if (event.getType().equals(OrderReadyEvent.class.getName())) {
            OrderReadyEvent readyEvent = fromJson(event.getData(), OrderReadyEvent.class);
            apply(readyEvent);
        } else if (event.getType().equals(OrderGiveEvent.class.getName())) {
            OrderGiveEvent giveEvent = fromJson(event.getData(), OrderGiveEvent.class);
            apply(giveEvent);
        } else if (event.getType().equals(OrderCanceledEvent.class.getName())) {
            OrderCanceledEvent canceledEvent = fromJson(event.getData(), OrderCanceledEvent.class);
            apply(canceledEvent);
        }
    }

    private <T extends BaseOrderEvent> T fromJson(String data, Class<T> type) {
        try {
            return (T) OBJECT_MAPPER.readValue(data, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
