package ru.almaz.OrderEventSourcing.events.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;
import ru.almaz.OrderEventSourcing.requests.OrderReadyRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderReadyEvent extends BaseOrderEvent {
    public OrderReadyEvent() {
    }

    public OrderReadyEvent(OrderReadyRequest request) {
        this.setEmployerId(request.employerId);
    }
}
