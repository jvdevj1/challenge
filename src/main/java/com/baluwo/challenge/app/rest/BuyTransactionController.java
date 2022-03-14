package com.baluwo.challenge.app.rest;

import com.baluwo.challenge.app.api.BuyTransactionApi;
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

@Api(value = "Buy-transaction API of Baluwo challenge", tags = {"Buy-transaction API"})
public interface BuyTransactionController {
    @ApiOperation(tags = "Buy-transaction API", value = "Find a buy-transaction by id.", notes = "If buy-transaction not exists, return error")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = BuyTransactionApi.class),
            @ApiResponse(code = 404, message = SwaggerConfiguration.API_RESPONSE_404, response = RestErrorApi.class)
    })
    @GetMapping
            (path = "/{buyTransactionId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<BuyTransactionApi> findById(
            @ApiParam(value = "buyTransactionId", required = true) @PathVariable(value = "buyTransactionId") Integer buyTransactionId);

    @ApiOperation(tags = "Buy-transaction API", value = "Get all buy-transactions.", notes = "Get all buy-transactions.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = BuyTransactionApi.class, responseContainer = "List")
    })
    @GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<BuyTransactionApi>> findAll();

    @ApiOperation(tags = "Buy-transaction API", value = "Create a new buy-transaction.", notes = "Buy-transaction id must not exist (if id is provided).")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SwaggerConfiguration.API_RESPONSE_201, response = BuyTransactionApi.class),
            @ApiResponse(code = 409, message = SwaggerConfiguration.API_RESPONSE_409, response = RestErrorApi.class)
    })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<BuyTransactionApi> create(@RequestBody BuyTransactionApi buyTransactionApi);

    @ApiOperation(tags = "Buy-transaction API", value = "Replace or create a buy-transaction.", notes = "Replace a buy-transaction if its id exists, or create a new buy-transaction if its id not exists.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerConfiguration.API_RESPONSE_200, response = BuyTransactionApi.class),
            @ApiResponse(code = 201, message = SwaggerConfiguration.API_RESPONSE_201, response = BuyTransactionApi.class)
    })
    @PutMapping(path = "/{buyTransactionId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<BuyTransactionApi> update(
            @ApiParam(value = "buyTransactionId", required = true) @PathVariable(value = "buyTransactionId") Integer buyTransactionId,
            @ApiParam(value = "buyTransactionApi", name = "buyTransactionApi", required = true) @RequestBody BuyTransactionApi buyTransactionApi);

    @ApiOperation(tags = "Buy-transaction API", value = "Delete a buy-transaction by id.", notes = "If buy-transaction not exists, return error")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = SwaggerConfiguration.API_RESPONSE_204),
            @ApiResponse(code = 404, message = SwaggerConfiguration.API_RESPONSE_404, response = RestErrorApi.class)
    })
    @DeleteMapping(path = "/{buyTransactionId}")
    ResponseEntity<Void> delete(@ApiParam(value = "buyTransactionId", required = true) @PathVariable(value = "buyTransactionId") Integer buyTransactionId);
}
