package com.baluwo.challenge.domain.repository;

import com.baluwo.challenge.domain.entity.BuyTransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyTransactionRepository extends CrudRepository<BuyTransactionEntity, Integer> {
}
