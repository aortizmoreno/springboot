package com.kafka.checkout.api.exception;

public class CreditCardValidationException extends RuntimeException {
    public CreditCardValidationException(String message) {
        super(message);
    }
}