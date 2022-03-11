package com.baluwo.challenge.app.rest.error;

import com.baluwo.challenge.app.api.error.RestErrorDTO;
import com.baluwo.challenge.exception.ResourceDuplicatedIdException;
import com.baluwo.challenge.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestErrorDTO handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage());
        return RestErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .errors(Collections.singletonList(HttpStatus.NOT_FOUND.getReasonPhrase()))
                .build();
    }

    @ExceptionHandler(ResourceDuplicatedIdException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public RestErrorDTO handleResourceDuplicatedIdException(ResourceDuplicatedIdException ex) {
        log.error(ex.getMessage());
        return RestErrorDTO.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .errors(Collections.singletonList(HttpStatus.CONFLICT.getReasonPhrase()))
                .build();
    }
}
