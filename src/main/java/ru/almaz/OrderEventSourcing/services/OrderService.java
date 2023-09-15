package ru.almaz.OrderEventSourcing.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.almaz.OrderEventSourcing.events.AggregateEvent;
import ru.almaz.OrderEventSourcing.events.order.*;
import ru.almaz.OrderEventSourcing.exceptions.NotRegisteredException;
import ru.almaz.OrderEventSourcing.exceptions.NotRegisteredOrClosedException;
import ru.almaz.OrderEventSourcing.exceptions.OrderIsRegisteredException;
import ru.almaz.OrderEventSourcing.models.db.OrderEvent;
import ru.almaz.OrderEventSourcing.models.material.Order;
import ru.almaz.OrderEventSourcing.repositories.OrderEventRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderEventRepository orderEventRepository;
    private final ObjectMapper mapper;

    @Autowired
    public OrderService(OrderEventRepository orderEventRepository, ObjectMapper objectMapper) {
        this.orderEventRepository = orderEventRepository;
        this.mapper = objectMapper;
    }

    private String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public OrderEvent publishEvent(AggregateEvent event) {
        return orderEventRepository.save(
                OrderEvent.getInstance(event.getAggregateId(), event.getClass().getName(), toJson(event), new Date())
        );
    }

    public OrderEvent registerOrder(OrderCreatedEvent event) {
        event.setOrderId(orderEventRepository.nextOrderIdVal());
        return publishEvent(event);
    }

    public void updateOrder(AggregateEvent event) {
        if (isOrderRegisteredAndNotClosed(event.getAggregateId())) {
            publishEvent(event);
        } else {
            throw new NotRegisteredOrClosedException("Order is not registered or closed!");
        }
    }

    public Order getOrder(Long orderId) {
        List<OrderEvent> dbEvents = orderEventRepository.findAllByOrderId(orderId);

        if (dbEvents.size() ==  0) {
            throw new NotRegisteredException("Order is not found");
        }

        Order order = new Order();
        order.applyJsonAll(dbEvents);
        return order;
    }

    private boolean isOrderRegistered(Long orderId) {
        Optional<OrderEvent> registerEvent = orderEventRepository
                .findByOrderIdAndType(orderId, OrderCreatedEvent.class.getName());

        return registerEvent.isPresent();
    }

    private boolean isOrderRegisteredAndNotClosed(Long orderId) {
        Optional<OrderEvent> orderClosingEvent = orderEventRepository
                .findByOrderIdAndTypeIn(
                        orderId,
                        Arrays.asList(OrderCanceledEvent.class.getName(), OrderGiveEvent.class.getName())
                );

        return isOrderRegistered(orderId) && orderClosingEvent.isEmpty();
    }
}
