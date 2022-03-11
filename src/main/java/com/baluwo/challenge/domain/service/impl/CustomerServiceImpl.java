package com.baluwo.challenge.domain.service.impl;

import com.baluwo.challenge.domain.entity.CustomerEntity;
import com.baluwo.challenge.domain.repository.CustomerRepository;
import com.baluwo.challenge.domain.service.CustomerService;
import com.baluwo.challenge.exception.ResourceDuplicatedIdException;
import com.baluwo.challenge.exception.ResourceNotFoundException;
import com.baluwo.challenge.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity findById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.CUSTOMER, customerId));
    }

    @Override
    public boolean existsById(Integer customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public List<CustomerEntity> findAll() {
        return (List<CustomerEntity>)customerRepository.findAll();
    }

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        if (customerEntity.getId() == null ||
                (customerEntity.getId() != null && !customerRepository.existsById(customerEntity.getId()))) {
            return customerRepository.save(customerEntity);
        } else {
            throw new ResourceDuplicatedIdException(Constants.CUSTOMER, customerEntity.getId());
        }
    }

    @Override
    public CustomerEntity update(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @Override
    public void delete(Integer customerId) {
        customerRepository.findById(customerId)
                .map(customerEntity -> {
                    customerRepository.deleteById(customerId);
                    return customerEntity;
                })
                .orElseThrow(() -> new ResourceNotFoundException(Constants.CUSTOMER, customerId));
    }
}
