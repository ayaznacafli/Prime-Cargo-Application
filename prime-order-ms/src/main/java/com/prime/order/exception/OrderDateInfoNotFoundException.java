package com.prime.order.exception;

public class OrderDateInfoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3042686055658047285L;

    public OrderDateInfoNotFoundException() {
        super("Order date info not found.");
    }

    public OrderDateInfoNotFoundException(long id) {
        super(String.format("Order date info with id %d not found.", id));
    }

}
