package com.challenge.senior.services;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.entities.dtos.SolicitationDTO;
import com.challenge.senior.entities.dtos.SolicitationItemDTO;
import com.challenge.senior.entities.enums.SolicitationStatus;
import com.challenge.senior.entities.mappers.SolicitationItemMapper;
import com.challenge.senior.entities.mappers.SolicitationMapper;
import com.challenge.senior.exceptions.DatabaseException;
import com.challenge.senior.exceptions.ResourceNotFoundException;
import com.challenge.senior.repositories.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private SolicitationItemService solicitationItemService;

    public Solicitation findById(final UUID id) {
        Optional<Solicitation> order = solicitationRepository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    public List<Solicitation> findAll() {
        return solicitationRepository.findAll();
    }

    public Solicitation save(final SolicitationDTO solicitationDTO) {
        if (solicitationDTO.getRequester().trim().equals("")) throw new IllegalArgumentException("Requester is required");
        if (solicitationDTO.getDiscount() < 0) throw new IllegalArgumentException("The discount must be equal to or greater than 0");

        solicitationDTO.setSolicitationStatus(SolicitationStatus.WAITTING_PAYMENT);
        solicitationDTO.setSolicitationTime(Instant.now());

        Solicitation solicitation = SolicitationMapper.fromDtoToEntity(solicitationDTO);
        solicitation = solicitationRepository.save(solicitation);

        for (SolicitationItemDTO solicitationItemDTO : solicitationDTO.getItems()) {
            SolicitationItem solicitationItem = SolicitationItemMapper.fromDtoToEntity(solicitationItemDTO, solicitationDTO);

            solicitationItem = solicitationItemService.save(solicitationItem);
            solicitation.getItems().add(solicitationItem);
        }

        return solicitation;
    }

    public Solicitation update(final UUID id, final Solicitation solicitation) {
        if (solicitation.getRequester().trim().equals("")) throw new IllegalArgumentException("Requester is required");
        if (solicitation.getDiscount() < 0) throw new IllegalArgumentException("The discount must be equal to or greater than 0");

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
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("It's not possible to delete a solicitation that has items.");
        }
    }
}
