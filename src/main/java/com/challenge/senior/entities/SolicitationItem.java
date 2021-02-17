package com.challenge.senior.entities;

import com.challenge.senior.entities.pk.SolicitationItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class SolicitationItem implements Serializable {

    @EmbeddedId
    SolicitationItemPK id = new SolicitationItemPK();

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    public SolicitationItem() {}

    public SolicitationItem(final Solicitation solicitation,
                            final Product product,
                            final Integer quantity,
                            final Double price) {
        id.setSolicitation(solicitation);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Solicitation getSolicitation() {
        return this.id.getSolicitation();
    }

    public void setSolicitation(final Solicitation solicitation) {
        this.id.setSolicitation(solicitation);
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    public void setProduct(final Product product) {
        this.id.setProduct(product);
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitationItem solicitationItem = (SolicitationItem) o;
        return id.equals(solicitationItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
