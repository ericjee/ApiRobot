package com.vip.yyl.service.utils.swagger.core.config;

import com.vip.yyl.domain.apis.swagger.Swagger;

public interface SwaggerConfig {

    Swagger configure(Swagger swagger);

    String getFilterClass();
}
