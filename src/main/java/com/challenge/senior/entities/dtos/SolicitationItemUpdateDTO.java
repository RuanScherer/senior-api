package com.challenge.senior.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SolicitationItemUpdateDTO {

    private Integer quantity;

    @JsonIgnore
    private Double price;

    public SolicitationItemUpdateDTO() {}

    public SolicitationItemUpdateDTO(final Integer quantity, final Double price) {
        this.quantity = quantity;
        this.price = price;
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
