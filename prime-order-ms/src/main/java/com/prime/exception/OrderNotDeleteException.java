package com.prime.exception;

public class OrderNotDeleteException extends RuntimeException {
    private static final long serialVersionUID = -3042686055658047285L;

    public OrderNotDeleteException() {
        super("Order didn't delete.");
    }

    public OrderNotDeleteException(long id) {
        super(String.format("Order with id %d didn't delete.", id));
    }

}
