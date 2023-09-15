package ru.almaz.OrderEventSourcing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Заказ закрыт(выдан, отменен), либо не зарегистрирован")
public class NotRegisteredOrClosedException extends NotRegisteredException {
    public NotRegisteredOrClosedException(String message) {
        super(message);
    }
}
