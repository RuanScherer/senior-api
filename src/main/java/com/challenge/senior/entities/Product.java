package com.challenge.senior.entities;

import com.challenge.senior.entities.enums.ProductType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Product implements Serializable {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer type;

    public Product() {}

    public Product(final UUID id,
                   final String name,
                   final String description,
                   final Double price,
                   final ProductType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        setType(type);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public ProductType getType() {
        return ProductType.valueOf(this.type);
    }

    public void setType(final ProductType type) {
        if (type != null) {
            this.type = type.getCode();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
