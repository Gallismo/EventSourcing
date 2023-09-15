package ru.almaz.OrderEventSourcing.requests;

import jakarta.validation.constraints.NotNull;

public class OrderReadyRequest {
    @NotNull
    public Long employerId;
}
