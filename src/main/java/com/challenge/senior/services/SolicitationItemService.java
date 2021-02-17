package com.challenge.senior.services;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.entities.pk.SolicitationItemPK;
import com.challenge.senior.exceptions.ResourceNotFoundException;
import com.challenge.senior.repositories.SolicitationItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitationItemService {

    @Autowired
    private SolicitationItemRepository solicitationItemRepository;

    @Autowired
    private SolicitationService solicitationService;

    @Autowired
    private ProductService productService;

    public SolicitationItem save(final SolicitationItem solicitationItem) {
        return solicitationItemRepository.save(solicitationItem);
    }

    public List<SolicitationItem> findBySolicitationId(final UUID solicitationId) {
        final Solicitation solicitation = solicitationService.findById(solicitationId);
        return solicitationItemRepository.findAllByIdSolicitation(solicitation);
    }

    public SolicitationItem findById(final SolicitationItemPK solicitationItemId) {
        Optional<SolicitationItem> solicitationItem = solicitationItemRepository.findById(solicitationItemId);
        return solicitationItem.orElseThrow(() -> new ResourceNotFoundException(solicitationItemId.toString()));
    }

    public void delete(final SolicitationItemPK solicitationItemId) {
        try {
            solicitationItemRepository.deleteById(solicitationItemId);
        } catch (final EmptyResultDataAccessException exception) { // for invalid record ID
            throw new ResourceNotFoundException(solicitationItemId);
        }
    }
}
