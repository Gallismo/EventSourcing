package ru.almaz.OrderEventSourcing.events.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.almaz.OrderEventSourcing.events.BaseOrderEvent;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderReadyEvent extends BaseOrderEvent {
}
