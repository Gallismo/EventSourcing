package ru.almaz.OrderEventSourcing.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.almaz.OrderEventSourcing.events.order.*;
import ru.almaz.OrderEventSourcing.models.db.OrderEvent;
import ru.almaz.OrderEventSourcing.models.material.Order;
import ru.almaz.OrderEventSourcing.requests.*;
import ru.almaz.OrderEventSourcing.services.OrderService;

import java.util.Random;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/")
    public Order registerOrder(@Valid @RequestBody OrderCreateRequest createRequest) {
        OrderCreatedEvent createdEvent = new OrderCreatedEvent(createRequest);
        OrderEvent dbEvent = orderService.registerOrder(createdEvent);
        return orderService.getOrder(dbEvent.getOrderId());
    }

    @PatchMapping("/accept/{id}")
    public Order acceptOrder(@Valid @RequestBody OrderAcceptRequest acceptRequest, @PathVariable Long id) {
        OrderAcceptEvent event = new OrderAcceptEvent(acceptRequest);
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }

    @PatchMapping("/ready/{id}")
    public Order readyOrder(@Valid @RequestBody OrderReadyRequest readyRequest, @PathVariable Long id) {
        OrderReadyEvent event = new OrderReadyEvent(readyRequest);
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }

    @PatchMapping("/give/{id}")
    public Order giveOrder(@Valid @RequestBody OrderGiveRequest giveRequest, @PathVariable Long id) {
        OrderGiveEvent event = new OrderGiveEvent(giveRequest);
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }

    @PatchMapping("/cancel/{id}")
    public Order cancelOrder(@Valid @RequestBody OrderCancelRequest cancelRequest, @PathVariable Long id) {
        OrderCanceledEvent event = new OrderCanceledEvent(cancelRequest);
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }
}
