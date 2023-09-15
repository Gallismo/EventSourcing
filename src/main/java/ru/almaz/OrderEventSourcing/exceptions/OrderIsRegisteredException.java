package ru.almaz.OrderEventSourcing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Заказ с этим номером уже зарегистрирован")
public class OrderIsRegisteredException extends RuntimeException {
    public OrderIsRegisteredException(String message) {
        super(message);
    }
}
