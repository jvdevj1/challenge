package com.baluwo.challenge.app.rest.impl;

import com.baluwo.challenge.app.api.BuyTransactionApi;
import com.baluwo.challenge.app.rest.BuyTransactionController;
import com.baluwo.challenge.domain.entity.BuyTransactionEntity;
import com.baluwo.challenge.domain.mapper.BuyTransactionMapper;
import com.baluwo.challenge.domain.service.BuyTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${api.endpoints.buyTransaction.mapping}")
@Slf4j
public class BuyTransactionControllerImpl implements BuyTransactionController {
    private BuyTransactionService buyTransactionService;
    private BuyTransactionMapper buyTransactionMapper;

    public BuyTransactionControllerImpl(BuyTransactionService buyTransactionService, BuyTransactionMapper buyTransactionMapper) {
        this.buyTransactionService = buyTransactionService;
        this.buyTransactionMapper = buyTransactionMapper;
    }

    @Override
    public ResponseEntity<BuyTransactionApi> findById(Integer buyTransactionId) {
        BuyTransactionEntity buyTransactionEntity = buyTransactionService.findById(buyTransactionId);
        return new ResponseEntity<>(buyTransactionMapper.toBuyTransactionApi(buyTransactionEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BuyTransactionApi>> findAll() {
        List<BuyTransactionApi> results = buyTransactionMapper.toBuyTransactionApis(buyTransactionService.findAll());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuyTransactionApi> create(BuyTransactionApi buyTransactionApi) {
        BuyTransactionEntity newBuyTransactionEntity = buyTransactionService.create(buyTransactionMapper.toBuyTransactionEntity(buyTransactionApi));
        BuyTransactionApi newBuyTransactionApi = buyTransactionMapper.toBuyTransactionApi(newBuyTransactionEntity);
        return new ResponseEntity<>(newBuyTransactionApi, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuyTransactionApi> update(Integer buyTransactionId, BuyTransactionApi buyTransactionApi) {
        BuyTransactionEntity buyTransactionEntity = buyTransactionMapper.toBuyTransactionEntity(buyTransactionApi);
        BuyTransactionEntity buyTransactionEntityUpdated = null;

        if (buyTransactionService.existsById(buyTransactionId)) {
            buyTransactionEntity.setId(buyTransactionId);
            buyTransactionEntityUpdated = buyTransactionService.update(buyTransactionEntity);
            return new ResponseEntity<>(buyTransactionMapper.toBuyTransactionApi(buyTransactionEntityUpdated), HttpStatus.OK);
        } else {
            buyTransactionEntity.setId(buyTransactionId);
            buyTransactionEntityUpdated = buyTransactionService.update(buyTransactionEntity);
            log.info("But-transaction with id {} not exists, it has been created", buyTransactionId);
            return new ResponseEntity<>(buyTransactionMapper.toBuyTransactionApi(buyTransactionEntityUpdated), HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Void> delete(Integer buyTransactionId) {
        buyTransactionService.delete(buyTransactionId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
