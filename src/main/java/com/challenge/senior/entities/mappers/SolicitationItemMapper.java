package com.challenge.senior.entities.mappers;

import com.challenge.senior.entities.Product;
import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.entities.dtos.SolicitationDTO;
import com.challenge.senior.entities.dtos.SolicitationItemDTO;
import com.challenge.senior.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SolicitationItemMapper {

    @Autowired
    private ProductService productService;

    private static ProductService staticProductService;

    @Autowired
    public void setStaticProductService(final ProductService productService) {
        SolicitationItemMapper.staticProductService = productService;
    }

    public static SolicitationItem fromDtoToEntity(final SolicitationItemDTO solicitationItemDTO,
                                                   final SolicitationDTO solicitation) {
        final UUID id = solicitationItemDTO.getProductID();
        final Product product = staticProductService.findById(id);
        SolicitationItem solicitationItem = new SolicitationItem(
                SolicitationMapper.fromDtoToEntity(solicitation),
                product,
                solicitationItemDTO.getQuantity(),
                solicitationItemDTO.getPrice()
        );
        return solicitationItem;
    }
}
