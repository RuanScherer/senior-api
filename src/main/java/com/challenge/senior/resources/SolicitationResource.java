package com.challenge.senior.resources;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.SolicitationItem;
import com.challenge.senior.entities.dtos.SolicitationDTO;
import com.challenge.senior.entities.dtos.SolicitationItemDTO;
import com.challenge.senior.entities.mappers.SolicitationItemMapper;
import com.challenge.senior.entities.pk.SolicitationItemPK;
import com.challenge.senior.services.ProductService;
import com.challenge.senior.services.SolicitationItemService;
import com.challenge.senior.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/solicitations")
public class SolicitationResource {

    @Autowired
    private SolicitationService solicitationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SolicitationItemService solicitationItemService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Solicitation> findById(@PathVariable final UUID id) {
        return ResponseEntity.ok().body(solicitationService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Solicitation>> findAll() {
        return ResponseEntity.ok().body(solicitationService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Solicitation> create(@RequestBody SolicitationDTO solicitation) {
        Solicitation response = solicitationService.save(solicitation);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitation> update(@PathVariable final UUID id, @RequestBody Solicitation solicitation) {
        solicitation = solicitationService.update(id, solicitation);
        return ResponseEntity.ok().body(solicitation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        solicitationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/items")
    public ResponseEntity<List<SolicitationItem>> findAllItems(@PathVariable final UUID id) {
        return ResponseEntity.ok().body(solicitationItemService.findBySolicitationId(id));
    }

    @GetMapping(value = "/{id}/items/{productId}")
    public ResponseEntity<SolicitationItem> findItemById(@PathVariable final UUID id,
                                                         @PathVariable final UUID productId) {
        SolicitationItemPK solicitationItemId = new SolicitationItemPK(
                solicitationService.findById(id),
                productService.findById(productId)
        );
        return ResponseEntity.ok().body(solicitationItemService.findById(solicitationItemId));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<SolicitationItem> createItem(@PathVariable final UUID id,
                                                       @RequestBody SolicitationItemDTO solicitationItemDTO) {
        Solicitation solicitation = solicitationService.findById(id);

        SolicitationItem solicitationItem = SolicitationItemMapper.fromDtoToEntity(solicitationItemDTO, solicitation);
        solicitationItem = solicitationItemService.save(solicitationItem);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{productId}")
                .buildAndExpand(solicitationItem.getProduct().getId())
                .toUri();

        return ResponseEntity.created(uri).body(solicitationItem);
    }

    @DeleteMapping("/{id}/items/{productId}")
    public ResponseEntity<Void> deleteItem(@PathVariable final UUID id, @PathVariable final UUID productId) {
        SolicitationItemPK solicitationItemId = new SolicitationItemPK(
                solicitationService.findById(id),
                productService.findById(productId)
        );
        solicitationItemService.delete(solicitationItemId);
        return ResponseEntity.noContent().build();
    }
}
