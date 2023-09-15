package ru.almaz.OrderEventSourcing.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public abstract class BaseOrderEvent implements AggregateEvent {
    private Long orderId = null;

    private Long employerId;

    @JsonIgnore
    @Override
    public Long getAggregateId() {
        return getOrderId();
    }
}
