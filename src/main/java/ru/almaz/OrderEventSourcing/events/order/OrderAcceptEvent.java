package ru.almaz.OrderEventSourcing.events.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;
import ru.almaz.OrderEventSourcing.requests.OrderAcceptRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderAcceptEvent extends BaseOrderEvent {
    public OrderAcceptEvent(){}
    public OrderAcceptEvent(OrderAcceptRequest request) {
        this.setEmployerId(request.employerId);
    }
}
