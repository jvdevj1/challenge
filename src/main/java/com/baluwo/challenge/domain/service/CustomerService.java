package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerEntity findById(Integer customerId);
    boolean existsById(Integer customerId);
    List<CustomerEntity> findAll();
    CustomerEntity create(CustomerEntity customerEntity);
    CustomerEntity update(CustomerEntity customerEntity);
    void delete(Integer customerId);
}
