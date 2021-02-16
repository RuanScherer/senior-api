package com.challenge.senior.services;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.enums.SolicitationStatus;
import com.challenge.senior.exceptions.ResourceNotFoundException;
import com.challenge.senior.repositories.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitationService {

    @Autowired
    private SolicitationRepository solicitationRepository;

    public Solicitation findById(final UUID id) {
        Optional<Solicitation> order = solicitationRepository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    public List<Solicitation> findAll() {
        return solicitationRepository.findAll();
    }

    public Solicitation save(final Solicitation solicitation) {
        solicitation.setSolicitationStatus(SolicitationStatus.WAITTING_PAYMENT);
        solicitation.setSolicitationTime(Instant.now());
        return solicitationRepository.save(solicitation);
    }

    public Solicitation update(final UUID id, final Solicitation solicitation) {
        try {
            Solicitation entity = solicitationRepository.getOne(id);
            updateData(entity, solicitation);
            return solicitationRepository.save(entity);
        } catch (final EntityNotFoundException exception) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(final Solicitation entity, final Solicitation solicitation) {
        entity.setRequester(solicitation.getRequester());
        entity.setSolicitationStatus(solicitation.getSolicitationStatus());
    }

    public void delete(final UUID id) {
        try {
            solicitationRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException exception) { // for invalid record ID
            throw new ResourceNotFoundException(id);
        }
    }
}
