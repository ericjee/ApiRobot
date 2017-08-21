package com.vip.yyl.service.utils.swagger.core.util;


import com.vip.yyl.domain.apis.swagger.properties.PropertyBuilder;

import java.util.Map;

public interface AllowableValues {
    Map<PropertyBuilder.PropertyId, Object> asPropertyArguments();
}
