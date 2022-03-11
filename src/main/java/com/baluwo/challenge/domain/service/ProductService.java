package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductEntity> findById(Integer productId);
    boolean existsById(Integer productEntity);
    List<ProductEntity> findAll();
    ProductEntity create(ProductEntity productEntity);
    ProductEntity update(ProductEntity productEntity);
    void delete(Integer productId);
}
