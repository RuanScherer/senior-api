package com.challenge.senior.repositories;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.entities.pk.SolicitationItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitationItemRepository extends JpaRepository<SolicitationItem, SolicitationItemPK> {

    public List<SolicitationItem> findAllByIdSolicitation(Solicitation solicitation);
}
