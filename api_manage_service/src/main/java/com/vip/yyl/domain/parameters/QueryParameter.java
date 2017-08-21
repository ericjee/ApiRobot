package com.vip.yyl.domain.parameters;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "query")
public class QueryParameter extends AbstractParameter implements Parameter{

    public QueryParameter() {
        super.setIn("query");
    }

}
