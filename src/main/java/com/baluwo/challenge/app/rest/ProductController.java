package com.baluwo.challenge.app.rest;

import com.baluwo.challenge.app.api.ProductApi;
import com.baluwo.challenge.app.api.error.RestErrorApi;
import com.baluwo.challenge.config.SwaggerConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "Product API of Baluwo challenge", tags = {"Product API"})
public interface ProductController {
    @ApiOperation(tags = "Product API", value = "Find a product by id.", notes = "If product not exists, return error")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = ProductApi.class),
            @ApiResponse(code = 404, message = SwaggerConfiguration.API_RESPONSE_404, response = RestErrorApi.class)
    })
    @GetMapping
            (path = "/{productId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductApi> findById(
            @ApiParam(value = "productId", required = true) @PathVariable(value = "productId") Integer productId);

    @ApiOperation(tags = "Product API", value = "Get all products.", notes = "Get all products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = ProductApi.class, responseContainer = "List")
    })
    @GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductApi>> findAll();

    @ApiOperation(tags = "Product API", value = "Create a new product.", notes = "Product id must not exist (if id is provided).")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SwaggerConfiguration.API_RESPONSE_201, response = ProductApi.class),
            @ApiResponse(code = 409, message = SwaggerConfiguration.API_RESPONSE_409, response = RestErrorApi.class)
    })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductApi> create(@RequestBody ProductApi productApi);

    @ApiOperation(tags = "Product API", value = "Replace or create a product.", notes = "Replace a product if its id exists, or create a new product if its id not exists.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = ProductApi.class),
            @ApiResponse(code = 201, message = SwaggerConfiguration.API_RESPONSE_201, response = ProductApi.class)
    })
    @PutMapping(path = "/{productId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductApi> update(
            @ApiParam(value = "productId", required = true) @PathVariable(value = "productId") Integer productId,
            @ApiParam(value = "productApi", name = "productApi", required = true) @RequestBody ProductApi productApi);

    @ApiOperation(tags = "Product API", value = "Delete a product by id.", notes = "If product not exists, return error")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = SwaggerConfiguration.API_RESPONSE_204),
            @ApiResponse(code = 404, message = SwaggerConfiguration.API_RESPONSE_404, response = RestErrorApi.class)
    })
    @DeleteMapping(path = "/{productId}")
    ResponseEntity<Void> delete(@ApiParam(value = "productId", required = true) @PathVariable(value = "productId") Integer productId);
}
