package com.challenge.senior.entities.pk;

import com.challenge.senior.entities.Product;
import com.challenge.senior.entities.Solicitation;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SolicitationItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "solicitation_id")
    private Solicitation solicitation;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public SolicitationItemPK() {}

    public Solicitation getSolicitation() {
        return solicitation;
    }

    public void setSolicitation(final Solicitation solicitation) {
        this.solicitation = solicitation;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitationItemPK pk = (SolicitationItemPK) o;
        return solicitation.equals(pk.solicitation) && product.equals(pk.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solicitation, product);
    }
}
