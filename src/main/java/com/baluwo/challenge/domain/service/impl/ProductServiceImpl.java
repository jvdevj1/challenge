package com.baluwo.challenge.domain.service.impl;

import com.baluwo.challenge.domain.entity.ProductEntity;
import com.baluwo.challenge.domain.repository.ProductRepository;
import com.baluwo.challenge.domain.service.ProductService;
import com.baluwo.challenge.exception.ResourceDuplicatedIdException;
import com.baluwo.challenge.exception.ResourceNotFoundException;
import com.baluwo.challenge.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity findById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.PRODUCT, productId));
    }

    @Override
    public boolean existsById(Integer productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public List<ProductEntity> findAll() {
        return (List<ProductEntity>)productRepository.findAll();
    }

    @Override
    public ProductEntity create(ProductEntity productEntity) {
        if (productEntity.getId() == null ||
                (productEntity.getId() != null && !productRepository.existsById(productEntity.getId()))) {
            return productRepository.save(productEntity);
        } else {
            throw new ResourceDuplicatedIdException(Constants.PRODUCT, productEntity.getId());
        }
    }

    @Override
    public ProductEntity update(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public void delete(Integer productId) {
        productRepository.findById(productId)
                .map(productEntity -> {
                    productRepository.deleteById(productId);
                    return productEntity;
                })
                .orElseThrow(() -> new ResourceNotFoundException(Constants.PRODUCT, productId));
    }
}
