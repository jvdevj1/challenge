package com.baluwo.challenge.app.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "CustomerApi", description = "Customer")
public class CustomerApi {
    private Integer idCustomer;
    private String documentId;
    private String name;
    private String lastName;
}
