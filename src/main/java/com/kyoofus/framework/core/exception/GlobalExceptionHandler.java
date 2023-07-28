package com.kyoofus.framework.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class  })
    protected ResponseEntity<ErrorResponse>  handleCustomException(RuntimeException e) {
        log.error("handleCustomException throw CustomException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.OTHER_ERROR);
    }//:

}///

