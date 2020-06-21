package com.prime.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3042686055658047285L;

    public OrderNotFoundException() {
        super("Order not found.");
    }

    public OrderNotFoundException(long id) {
        super(String.format("Order with id %d not found.", id));
    }

}
