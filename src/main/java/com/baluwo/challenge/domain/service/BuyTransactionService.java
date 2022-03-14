package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.entity.BuyTransactionEntity;

import java.util.List;

public interface BuyTransactionService {
    BuyTransactionEntity findById(Integer buyTransactionId);
    boolean existsById(Integer buyTransactionId);
    List<BuyTransactionEntity> findAll();
    BuyTransactionEntity create(BuyTransactionEntity buyTransactionEntity);
    BuyTransactionEntity update(BuyTransactionEntity buyTransactionEntity);
    void delete(Integer buyTransactionId);
}
