package com.challenge.senior.repositories;

import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.entities.pk.SolicitationItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitationItemRepository extends JpaRepository<SolicitationItem, SolicitationItemPK> {
}
