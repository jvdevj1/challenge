package com.baluwo.challenge.app.rest;

import com.baluwo.challenge.app.api.CustomerApi;
import com.baluwo.challenge.domain.entity.CustomerEntity;
import com.baluwo.challenge.domain.mapper.CustomerMapper;
import com.baluwo.challenge.domain.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${api.endpoints.customer.mapping}")
@Slf4j
public class CustomerControllerImpl extends CustomController implements CustomerController {
    private CustomerService customerService;
    private CustomerMapper customerMapper;

    public CustomerControllerImpl(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public ResponseEntity<CustomerApi> findById(Integer customerId) {
        CustomerEntity customerEntity = customerService.findById(customerId);
        return new ResponseEntity<>(customerMapper.toCustomerApi(customerEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CustomerApi>> findAll() {
        List<CustomerApi> results = customerMapper.toCustomerApis(customerService.findAll());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerApi> create(CustomerApi customerApi) {
        CustomerEntity newCustomerEntity = customerService.create(customerMapper.toCustomerEntity(customerApi));
        CustomerApi newCustomerApi = customerMapper.toCustomerApi(newCustomerEntity);
        return new ResponseEntity<>(newCustomerApi, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerApi> update(Integer customerId, CustomerApi customerApi) {
        CustomerEntity customerEntity = customerMapper.toCustomerEntity(customerApi);
        CustomerEntity customerEntityUpdated = null;

        if (customerService.existsById(customerId)) {
            customerEntity.setId(customerId);
            customerEntityUpdated = customerService.update(customerEntity);
            return new ResponseEntity<>(customerMapper.toCustomerApi(customerEntityUpdated), HttpStatus.OK);
        } else {
            customerEntity.setId(customerId);
            customerEntityUpdated = customerService.update(customerEntity);
            log.info("Customer with id {} not exists, it has been created", customerEntity);
            return new ResponseEntity<>(customerMapper.toCustomerApi(customerEntityUpdated), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Void> delete(Integer customerId) {
        customerService.delete(customerId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
