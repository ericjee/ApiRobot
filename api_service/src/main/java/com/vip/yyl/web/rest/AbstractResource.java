package com.vip.yyl.web.rest;

import com.vip.yyl.domain.ApiResponseOwn;
import com.vip.yyl.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AbstractResource {

    @CrossOrigin(allowedHeaders = "foo", origins = "*")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiResponseOwn exception(NotFoundException e) {
        return new ApiResponseOwn(ApiResponseOwn.ERROR, e.getMessage(),new Object());
    }
}
