package ru.almaz.OrderEventSourcing.events.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends BaseOrderEvent {
    private Long clientId;
    private Date expectedTime = new Date(new Date().getTime() + 5 * 60 * 1000);
    private Long productId;
    private Integer productPrice;
}
