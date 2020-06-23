package com.prime.exception;

public class OrderQuantityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3042686055658047285L;

    public OrderQuantityNotFoundException() {
        super("Order Quantity not found.");
    }

    public OrderQuantityNotFoundException(long id) {
        super(String.format("Order Quantity with id %d not found.", id));

    }

}
