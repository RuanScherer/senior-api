package com.challenge.senior.repositories;

import com.challenge.senior.entities.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation, UUID> {
}
