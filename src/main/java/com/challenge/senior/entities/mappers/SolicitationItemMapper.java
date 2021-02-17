package com.challenge.senior.entities.mappers;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.entities.dtos.SolicitationDTO;
import com.challenge.senior.entities.dtos.SolicitationItemDTO;

public class SolicitationItemMapper {

    public static SolicitationItem fromDtoToEntity(final SolicitationItemDTO solicitationItemDTO,
                                                   final SolicitationDTO solicitation) {
        SolicitationItem solicitationItem = new SolicitationItem(
                SolicitationMapper.fromDtoToEntity(solicitation),
                solicitationItemDTO.getProduct(),
                solicitationItemDTO.getQuantity(),
                solicitationItemDTO.getPrice()
        );
        return solicitationItem;
    }
}
