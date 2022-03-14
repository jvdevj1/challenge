package com.baluwo.challenge.app.rest.impl;

import com.baluwo.challenge.app.api.ProductApi;
import com.baluwo.challenge.app.rest.ProductController;
import com.baluwo.challenge.domain.entity.ProductEntity;
import com.baluwo.challenge.domain.mapper.ProductMapper;
import com.baluwo.challenge.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${api.endpoints.product.mapping}")
@Slf4j
public class ProductControllerImpl implements ProductController {
    private ProductService productService;
    private ProductMapper productMapper;

    public ProductControllerImpl(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<ProductApi> findById(Integer productId) {
        ProductEntity productEntity = productService.findById(productId);
        return new ResponseEntity<>(productMapper.toProductApi(productEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductApi>> findAll() {
        List<ProductApi> results = productMapper.toProductApis(productService.findAll());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductApi> create(ProductApi productApi) {
        ProductEntity newProductEntity = productService.create(productMapper.toProductEntity(productApi));
        ProductApi newProductApi = productMapper.toProductApi(newProductEntity);
        return new ResponseEntity<>(newProductApi, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductApi> update(Integer productId, ProductApi productApi) {
        ProductEntity productEntity = productMapper.toProductEntity(productApi);
        ProductEntity productEntityUpdated = null;

        if (productService.existsById(productId)) {
            productEntity.setId(productId);
            productEntityUpdated = productService.update(productEntity);
            return new ResponseEntity<>(productMapper.toProductApi(productEntityUpdated), HttpStatus.OK);
        } else {
            productEntity.setId(productId);
            productEntityUpdated = productService.update(productEntity);
            log.info("Porduct with id {} not exists, it has been created", productId);
            return new ResponseEntity<>(productMapper.toProductApi(productEntityUpdated), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Void> delete(Integer productId) {
        productService.delete(productId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
