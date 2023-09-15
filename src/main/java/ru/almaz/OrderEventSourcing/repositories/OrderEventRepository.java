package ru.almaz.OrderEventSourcing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.almaz.OrderEventSourcing.models.db.OrderEvent;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {

    List<OrderEvent> findAllByOrderId(Long orderId);
    Optional<OrderEvent> findByOrderIdAndType(Long orderId, String type);

    Optional<OrderEvent> findByOrderIdAndTypeIn(Long orderId, Collection<String> type);
}
