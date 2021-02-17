package com.challenge.senior.resources;

import com.challenge.senior.entities.Solicitation;
import com.challenge.senior.entities.dtos.SolicitationDTO;
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
}
