package com.challenge.senior.services;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.repositories.SolicitationItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SolicitationItemService {

    @Autowired
    private SolicitationItemRepository solicitationItemRepository;

    @Autowired
    private SolicitationService solicitationService;

    public SolicitationItem save(final SolicitationItem solicitationItem) {
        return solicitationItemRepository.save(solicitationItem);
    }

    public List<SolicitationItem> findBySolicitationId(final UUID solicitationId) {
        Solicitation solicitation = solicitationService.findById(solicitationId);
        return solicitationItemRepository.findAllByIdSolicitation(solicitation);
    }
}
