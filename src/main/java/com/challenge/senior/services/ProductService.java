package com.challenge.senior.services;

import com.challenge.senior.entities.Product;
import com.challenge.senior.exceptions.DatabaseException;
import com.challenge.senior.exceptions.ResourceNotFoundException;
import com.challenge.senior.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        verifyData(product);
        return productRepository.save(product);
    }

    public Product update(final UUID id, final Product product) {
        verifyData(product);
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
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("It's not possible to delete a product or service that is linked to an solicitation.");
        }
    }

    public void verifyData(final Product product) {
        if (product.getName().trim().equals("")) throw new IllegalArgumentException("Name is required");
        if (product.getPrice() <= 0) throw new IllegalArgumentException("Price must be greater than 0");
    }
}
