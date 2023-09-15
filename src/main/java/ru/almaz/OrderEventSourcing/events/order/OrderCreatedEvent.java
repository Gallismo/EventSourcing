package ru.almaz.OrderEventSourcing.events.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;
import ru.almaz.OrderEventSourcing.requests.OrderCreateRequest;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends BaseOrderEvent {
    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(OrderCreateRequest request) {
        this.setClientId(request.clientId);
        this.setProductId(request.productId);
        this.setProductPrice(request.productPrice);
        this.setEmployerId(request.employerId);
    }

    private Long clientId;
    private Date expectedTime = new Date(new Date().getTime() + 5 * 60 * 1000);
    private Long productId;
    private Integer productPrice;
}
