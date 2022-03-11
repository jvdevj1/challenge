package com.baluwo.challenge.app.api.error;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ApiModel(value = "Error", description = "Error DTO")
public class RestErrorDTO {
    private Integer status;
    private String message;
    private List<String> errors;
}
