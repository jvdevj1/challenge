package com.baluwo.challenge.app.api.error;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ApiModel(value = "RestErrorApi", description = "Error")
public class RestErrorApi {
    private Integer status;
    private String message;
    private List<String> errors;
}
