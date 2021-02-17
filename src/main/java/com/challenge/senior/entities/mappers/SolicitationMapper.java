package com.challenge.senior.entities.mappers;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.dtos.SolicitationDTO;

public class SolicitationMapper {

    public static Solicitation fromDtoToEntity(final SolicitationDTO solicitationDTO) {
        Solicitation solicitation = new Solicitation(
                solicitationDTO.getId(),
                solicitationDTO.getRequester(),
                solicitationDTO.getSolicitationStatus(),
                solicitationDTO.getSolicitationTime()
        );
        return solicitation;
    }
}
