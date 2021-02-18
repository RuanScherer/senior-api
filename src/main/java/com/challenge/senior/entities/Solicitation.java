package com.challenge.senior.entities;

import com.challenge.senior.entities.enums.ProductType;
import com.challenge.senior.entities.enums.SolicitationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Solicitation implements Serializable {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String requester;

    @Column(nullable = false)
    private Integer solicitationStatus;

    @Column(nullable = false)
    private Instant solicitationTime;

    @OneToMany(mappedBy = "id.solicitation", cascade = CascadeType.REMOVE)
    private Set<SolicitationItem> items = new HashSet<>();

    private Double discount;

    public Solicitation() {}

    public Solicitation(final UUID id,
                        final String requester,
                        final SolicitationStatus solicitationStatus,
                        final Instant solicitationTime,
                        final Double discount) {
        this.id = id;
        this.requester = requester;
        setSolicitationStatus(solicitationStatus);
        this.solicitationTime = solicitationTime;
        this.discount = discount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(final String requester) {
        this.requester = requester;
    }

    public SolicitationStatus getSolicitationStatus() {
        return SolicitationStatus.valueOf(this.solicitationStatus);
    }

    public void setSolicitationStatus(final SolicitationStatus solicitationStatus) {
        if (solicitationStatus != null) {
            this.solicitationStatus = solicitationStatus.getCode();
        }
    }

    public Instant getSolicitationTime() {
        return solicitationTime;
    }

    public void setSolicitationTime(final Instant orderTime) {
        this.solicitationTime = orderTime;
    }

    public Set<SolicitationItem> getItems() {
        return items;
    }

    public Double getDiscount() {
        if (this.discount != null) {
            return discount;
        }
        return 0.0;
    }

    public void setDiscount(final Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        Double servicesTotal = 0.0;
        Double productsTotal = 0.0;

        for (SolicitationItem solicitationItem : this.getItems()) {
            if (solicitationItem.getProduct().getType() == ProductType.PRODUCT) {
                productsTotal += solicitationItem.getPrice();
            } else {
                servicesTotal += solicitationItem.getPrice();
            }
        }

        productsTotal -= productsTotal * (this.getDiscount() / 100);

        return servicesTotal + productsTotal;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitation solicitation = (Solicitation) o;
        return id.equals(solicitation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
