package com.challenge.senior.services;

import com.challenge.senior.entities.Product;
import com.challenge.senior.exceptions.ResourceNotFoundException;
import com.challenge.senior.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findById(final UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(final Product product) {
        return productRepository.save(product);
    }

    public Product update(final UUID id, final Product product) {
        try {
            Product entity = productRepository.getOne(id);
            updateData(entity, product);
            return productRepository.save(entity);
        } catch (final EntityNotFoundException exception) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(final Product entity, final Product product) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setType(product.getType());
    }

    public void delete(final UUID id) {
        try {
            productRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException exception) { // for invalid record ID
            throw new ResourceNotFoundException(id);
        }
    }
}
