package com.prime.order.exception;


public class OrderNotUpdateException extends RuntimeException {
    private static final long serialVersionUID = -3042686055658047285L;

    public OrderNotUpdateException() {
        super("Order didn't update.");
    }

    public OrderNotUpdateException(long id) {
        super(String.format("Order with id %d didn't update.", id));
    }

}
