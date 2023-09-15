package ru.almaz.OrderEventSourcing.requests;

import jakarta.validation.constraints.NotNull;

public class OrderCancelRequest {
    @NotNull
    public String cancelReason;
    @NotNull
    public Long employerId;
}
