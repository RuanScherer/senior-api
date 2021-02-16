package com.challenge.senior.resources;

import com.challenge.senior.entities.Product;
import com.challenge.senior.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products-services")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable final UUID id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Product product) {
        product = productService.save(product);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(product.getId())
                    .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable final UUID id, @RequestBody Product product) {
        product = productService.update(id, product);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
