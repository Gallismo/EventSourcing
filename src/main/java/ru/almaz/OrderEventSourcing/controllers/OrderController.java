package ru.almaz.OrderEventSourcing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.almaz.OrderEventSourcing.events.order.*;
import ru.almaz.OrderEventSourcing.models.material.Order;
import ru.almaz.OrderEventSourcing.services.OrderService;

import java.util.Random;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final Random random;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        this.random = new Random();
    }


    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/{id}")
    public Order registerOrder(@RequestBody OrderCreatedEvent createdEvent, @PathVariable Long id) throws Exception {
        createdEvent.setOrderId(id);
        orderService.registerOrder(createdEvent);
        return orderService.getOrder(id);
    }

    @PatchMapping("/accept/{id}")
    public Order acceptOrder(@RequestBody OrderAcceptEvent event, @PathVariable Long id) throws Exception {
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }

    @PatchMapping("/ready/{id}")
    public Order readyOrder(@RequestBody OrderReadyEvent event, @PathVariable Long id) throws Exception {
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }

    @PatchMapping("/give/{id}")
    public Order giveOrder(@RequestBody OrderGiveEvent event, @PathVariable Long id) throws Exception {
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }

    @PatchMapping("/cancel/{id}")
    public Order cancelOrder(@RequestBody OrderCanceledEvent event, @PathVariable Long id) throws Exception {
        event.setOrderId(id);
        orderService.updateOrder(event);
        return orderService.getOrder(id);
    }
}
