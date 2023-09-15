package ru.almaz.OrderEventSourcing.events.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;
import ru.almaz.OrderEventSourcing.requests.OrderCancelRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCanceledEvent extends BaseOrderEvent {
    public OrderCanceledEvent() {
    }

    public OrderCanceledEvent(OrderCancelRequest request) {
        this.setEmployerId(request.employerId);
        this.setCancelReason(request.cancelReason);
    }

    private String cancelReason;
}
