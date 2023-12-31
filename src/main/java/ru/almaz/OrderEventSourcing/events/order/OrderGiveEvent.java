package ru.almaz.OrderEventSourcing.events.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;
import ru.almaz.OrderEventSourcing.requests.OrderGiveRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderGiveEvent extends BaseOrderEvent {
    public OrderGiveEvent() {
    }

    public OrderGiveEvent(OrderGiveRequest request) {
        this.setEmployerId(request.employerId);
    }
}
