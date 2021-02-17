package com.challenge.senior.entities.dtos;

import com.challenge.senior.entities.enums.SolicitationStatus;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SolicitationDTO {

    private UUID id = UUID.randomUUID();

    private String requester;

    private Integer solicitationStatus;

    private Instant solicitationTime;

    private Set<SolicitationItemDTO> items = new HashSet<>();

    private Double discount;

    public SolicitationDTO() {}

    public SolicitationDTO(final String requester,
                           final SolicitationStatus solicitationStatus,
                           final Instant solicitationTime,
                           final Double discount) {
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

    public Set<SolicitationItemDTO> getItems() {
        return items;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(final Double discount) {
        this.discount = discount;
    }
}
