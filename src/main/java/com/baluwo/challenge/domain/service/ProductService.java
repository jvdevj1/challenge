package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity findById(Integer productId);
    boolean existsById(Integer productId);
    List<ProductEntity> findAll();
    ProductEntity create(ProductEntity productEntity);
    ProductEntity update(ProductEntity productEntity);
    void delete(Integer productId);
}
