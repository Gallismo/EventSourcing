package ru.almaz.OrderEventSourcing.models.db;

import jakarta.persistence.*;
import lombok.Data;
import ru.almaz.OrderEventSourcing.models.material.Order;

import java.util.Date;

@Entity
@Table(name = "orders_events")
@Data
public class OrderEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_id_sequence", allocationSize = 10)
    private Long orderId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "data", columnDefinition = "JSON", nullable = false)
    private String data;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public static OrderEvent getInstance(Long orderId, String type, String data, Date creationDate) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.orderId = orderId;
        orderEvent.type = type;
        orderEvent.data = data;
        orderEvent.creationDate = creationDate;
        return orderEvent;
    }
}
