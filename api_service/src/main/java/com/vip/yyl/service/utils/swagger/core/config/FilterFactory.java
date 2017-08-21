package com.vip.yyl.service.utils.swagger.core.config;


import com.vip.yyl.service.utils.swagger.core.filter.SwaggerSpecFilter;

public class FilterFactory {
    protected static SwaggerSpecFilter FILTER = null;

    public static SwaggerSpecFilter getFilter() {
        return FILTER;
    }

    public static void setFilter(SwaggerSpecFilter filter) {
        FILTER = filter;
    }
}
