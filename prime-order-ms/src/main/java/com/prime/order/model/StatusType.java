package com.prime.order.model;

public enum StatusType {
    NEW(0,"New"),
    PENDING(1,"Pending"),
    APPROVED(2,"Approved"),
    FOREIGN(3,"Foreign"),
    SEND(4,"Send"),
    LOCAL(5,"Local"),
    COMPETED(6,"Competed");

    private int id;
    private String message;

    StatusType(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
