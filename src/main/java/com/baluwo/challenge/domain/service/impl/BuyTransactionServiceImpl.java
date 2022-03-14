package com.baluwo.challenge.domain.service.impl;

import com.baluwo.challenge.domain.entity.BuyTransactionEntity;
import com.baluwo.challenge.domain.repository.BuyTransactionRepository;
import com.baluwo.challenge.domain.service.BuyTransactionService;
import com.baluwo.challenge.exception.ResourceDuplicatedIdException;
import com.baluwo.challenge.exception.ResourceNotFoundException;
import com.baluwo.challenge.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BuyTransactionServiceImpl implements BuyTransactionService {
    private BuyTransactionRepository buyTransactionRepository;

    public BuyTransactionServiceImpl(BuyTransactionRepository buyTransactionRepository) {
        this.buyTransactionRepository = buyTransactionRepository;
    }

    @Override
    public BuyTransactionEntity findById(Integer buyTransactionId) {
        return buyTransactionRepository.findById(buyTransactionId)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.BUY_TRANSACTION, buyTransactionId));
    }

    @Override
    public boolean existsById(Integer buyTransactionId) {
        return buyTransactionRepository.existsById(buyTransactionId);
    }

    @Override
    public List<BuyTransactionEntity> findAll() {
        return (List<BuyTransactionEntity>)buyTransactionRepository.findAll();
    }

    @Override
    public BuyTransactionEntity create(BuyTransactionEntity buyTransactionEntity) {
        if (buyTransactionEntity.getId() == null ||
                (buyTransactionEntity.getId() != null && !buyTransactionRepository.existsById(buyTransactionEntity.getId()))) {
            return buyTransactionRepository.save(buyTransactionEntity);
        } else {
            throw new ResourceDuplicatedIdException(Constants.BUY_TRANSACTION, buyTransactionEntity.getId());
        }
    }

    @Override
    public BuyTransactionEntity update(BuyTransactionEntity buyTransactionEntity) {
        return buyTransactionRepository.save(buyTransactionEntity);
    }

    @Override
    public void delete(Integer buyTransactionId) {
        buyTransactionRepository.findById(buyTransactionId)
                .map(buyTransactionEntity -> {
                    buyTransactionRepository.deleteById(buyTransactionId);
                    return buyTransactionEntity;
                })
                .orElseThrow(() -> new ResourceNotFoundException(Constants.BUY_TRANSACTION, buyTransactionId));
    }
}
