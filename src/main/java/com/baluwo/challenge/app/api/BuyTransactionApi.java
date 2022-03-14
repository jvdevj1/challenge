package com.baluwo.challenge.app.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "BuyTransactionApi", description = "Buy transaction")
public class BuyTransactionApi {
    private Integer idBuyTransaction;
    private CustomerApi customer;
    private List<ProductApi> products;
}
