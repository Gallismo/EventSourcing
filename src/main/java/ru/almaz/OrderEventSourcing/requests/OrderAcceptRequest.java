package ru.almaz.OrderEventSourcing.requests;

import jakarta.validation.constraints.NotNull;

public class OrderAcceptRequest {
    @NotNull
    public Long employerId;
}
