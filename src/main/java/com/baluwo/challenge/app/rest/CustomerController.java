package com.baluwo.challenge.app.rest;

import com.baluwo.challenge.app.api.CustomerApi;
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

@Api(value = "Customer API of Baluwo challenge", tags = {"Customer API"})
public interface CustomerController {
    @ApiOperation(tags = "Customer API", value = "Find a customer by id.", notes = "If customer not exists, return error")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = CustomerApi.class),
            @ApiResponse(code = 404, message = SwaggerConfiguration.API_RESPONSE_404, response = RestErrorApi.class)
    })
    @GetMapping
            (path = "/{customerId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<CustomerApi> findById(
            @ApiParam(value = "customerId", required = true) @PathVariable(value = "customerId") Integer customerId);

    @ApiOperation(tags = "Customer API", value = "Get all customers.", notes = "Get all customers.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = CustomerApi.class, responseContainer = "List")
    })
    @GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<CustomerApi>> findAll();

    @ApiOperation(tags = "Customer API", value = "Create a new customer.", notes = "Customer id must not exist (if id is provided).")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SwaggerConfiguration.API_RESPONSE_201, response = CustomerApi.class),
            @ApiResponse(code = 409, message = SwaggerConfiguration.API_RESPONSE_409, response = RestErrorApi.class)
    })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<CustomerApi> create(@RequestBody CustomerApi customerApi);

    @ApiOperation(tags = "Customer API", value = "Replace or create a customer.", notes = "Replace a customer if its id exists, or create a new customer if its id not exists.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = CustomerApi.class),
            @ApiResponse(code = 201, message = SwaggerConfiguration.API_RESPONSE_201, response = CustomerApi.class)
    })
    @PutMapping(path = "/{customerId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<CustomerApi> update(
            @ApiParam(value = "customerId", required = true) @PathVariable(value = "customerId") Integer customerId,
            @ApiParam(value = "customerApi", name = "customerApi", required = true) @RequestBody CustomerApi customerApi);

    @ApiOperation(tags = "Customer API", value = "Delete a customer by id.", notes = "If customer not exists, return error")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = SwaggerConfiguration.API_RESPONSE_204),
            @ApiResponse(code = 404, message = SwaggerConfiguration.API_RESPONSE_404, response = RestErrorApi.class)
    })
    @DeleteMapping(path = "/{customerId}")
    ResponseEntity<Void> delete(@ApiParam(value = "customerId", required = true) @PathVariable(value = "customerId") Integer customerId);
}
