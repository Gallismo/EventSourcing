package ru.almaz.OrderEventSourcing.events;

public interface AggregateEvent {
    Long getAggregateId();
}
