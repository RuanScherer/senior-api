package com.challenge.senior.entities.enums;

public enum SolicitationStatus {
    WAITTING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    private SolicitationStatus(final int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static SolicitationStatus valueOf(int code) {
        for (SolicitationStatus value : SolicitationStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status code.");
    }
}
