package com.tag.exceptions;

import com.tag.constants.ConstantMessages;
import feign.FeignException;
import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // to handle only Runtime exception
    @ExceptionHandler
    public ResponseEntity < String > handlerClientNotActiveException(ClientNotActiveException e) {
        log.info("handlerClientNotActiveException is called");
        System.out.println("Client is not active" + e.getMessage());
        return new ResponseEntity < > (ConstantMessages.FEIGN_ACTIVITY, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public ResponseEntity < String > handleFeignException(FeignException ex) {
        System.out.println("Feign exception occurred: " + ex.getMessage());
        return new ResponseEntity < > (ConstantMessages.FEIGN_ACTIVITY + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RedisException.class)
    @ResponseBody
    public ResponseEntity < String > handlerRedisException(RedisException ex) {
        // this is for showing on the console only
        log.info("handlerRedisException method is called");
        System.out.println(ConstantMessages.REDIS_ERROR + ex.getMessage());

        return new ResponseEntity < > (ConstantMessages.REDIS_ERROR + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity < String > handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.info("handlerHttpMessageNotReadableException is Called");
        System.out.println(ConstantMessages.HTTPS_ERROR + ex.getMessage());
        return new ResponseEntity < > (ConstantMessages.HTTPS_ERROR + ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity < String > handlerMethodNotAllowed(MethodArgumentNotValidException ex) {
        log.info("handlerMethodNotAllowed is Called");
        System.out.println(ConstantMessages.METHOD_ERROR + ex.getMessage());
        return new ResponseEntity < > (ConstantMessages.METHOD_ERROR + ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}