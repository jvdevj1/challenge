package com.baluwo.challenge.domain.mapper;

import com.baluwo.challenge.app.api.BuyTransactionApi;
import com.baluwo.challenge.domain.entity.BuyTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(uses = {CustomerMapper.class, ProductMapper.class},
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BuyTransactionMapper {
    @Mapping(source = "id", target = "idBuyTransaction")
    BuyTransactionApi toBuyTransactionApi(BuyTransactionEntity buyTransactionEntity);

    @Mapping(source = "idBuyTransaction", target = "id")
    BuyTransactionEntity toBuyTransactionEntity(BuyTransactionApi buyTransactionApi);

    List<BuyTransactionApi> toBuyTransactionApis(List<BuyTransactionEntity> buyTransactionEntity);

    List<BuyTransactionEntity> toBuyTransactionEntities(List<BuyTransactionApi> buyTransactionApi);
}
