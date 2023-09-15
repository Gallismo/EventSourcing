package ru.almaz.OrderEventSourcing.requests;

import jakarta.validation.constraints.NotNull;

public class OrderCreateRequest {
    @NotNull
    public Long clientId;
    @NotNull
    public Long productId;
    @NotNull
    public Integer productPrice;
    @NotNull
    public Long employerId;
}
