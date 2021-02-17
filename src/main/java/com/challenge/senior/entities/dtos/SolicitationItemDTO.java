package com.challenge.senior.entities.dtos;

import com.challenge.senior.entities.Product;
import java.io.Serializable;

public class SolicitationItemDTO implements Serializable {

    private Product product;

    private Integer quantity;

    private Double price;

    public SolicitationItemDTO() {}

    public SolicitationItemDTO(final Product product,
                               final Integer quantity,
                               final Double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(final Product product) {
        this.product = product;
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

