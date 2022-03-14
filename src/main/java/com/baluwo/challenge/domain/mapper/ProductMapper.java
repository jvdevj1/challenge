package com.baluwo.challenge.domain.mapper;

import com.baluwo.challenge.app.api.ProductApi;
import com.baluwo.challenge.domain.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {
    @Mapping(source = "id", target = "idProduct")
    ProductApi toProductApi(ProductEntity productEntity);

    @Mapping(source = "idProduct", target = "id")
    ProductEntity toProductEntity(ProductApi productApi);

    List<ProductApi> toProductApis(List<ProductEntity> productEntity);

    List<ProductEntity> toProductEntities(List<ProductApi> productApi);
}
