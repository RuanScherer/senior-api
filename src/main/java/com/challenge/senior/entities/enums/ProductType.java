package com.challenge.senior.entities.enums;

public enum ProductType {
    PRODUCT(1),
    SERVICE(2);

    private int code;

    private ProductType(final int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ProductType valueOf(final int code) {
        for (ProductType value : ProductType.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid product type code");
    }
}
