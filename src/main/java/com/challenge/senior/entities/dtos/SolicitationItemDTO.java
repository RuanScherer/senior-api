package com.challenge.senior.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.UUID;

public class SolicitationItemDTO implements Serializable {

    private UUID productID;

    private Integer quantity;

    @JsonIgnore
    private Double price;

    public SolicitationItemDTO() {}

    public SolicitationItemDTO(final UUID productID,
                               final Integer quantity,
                               final Double price) {
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getProductID() {
        return this.productID;
    }

    public void setProductID(final UUID productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}

