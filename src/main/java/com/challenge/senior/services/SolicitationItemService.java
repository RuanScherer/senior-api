package com.challenge.senior.services;

import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.repositories.SolicitationItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitationItemService {

    @Autowired
    private SolicitationItemRepository solicitationItemRepository;

    public SolicitationItem save(final SolicitationItem solicitationItem) {
        return solicitationItemRepository.save(solicitationItem);
    }
}
