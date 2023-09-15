package ru.almaz.OrderEventSourcing.requests;

import jakarta.validation.constraints.NotNull;

public class OrderGiveRequest {
    @NotNull
    public Long employerId;
}
