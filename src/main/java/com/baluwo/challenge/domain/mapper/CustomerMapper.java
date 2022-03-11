package com.baluwo.challenge.domain.mapper;

import com.baluwo.challenge.app.api.CustomerApi;
import com.baluwo.challenge.domain.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerMapper {
    @Mapping(source = "id", target = "idCustomer")
    CustomerApi toCustomerApi(CustomerEntity customerEntity);

    @Mapping(source = "idCustomer", target = "id")
    CustomerEntity toCustomerEntity(CustomerApi customerApi);

    List<CustomerApi> toCustomerApis(List<CustomerEntity> customerEntities);

    List<CustomerEntity> toCustomerEntities(List<CustomerApi> customerApis);
}
