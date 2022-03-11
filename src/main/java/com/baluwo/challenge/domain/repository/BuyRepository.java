package com.baluwo.challenge.domain.repository;

import com.baluwo.challenge.domain.entity.BuyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends CrudRepository<BuyEntity, String> {
}
