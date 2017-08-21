package com.vip.yyl.domain.parameters;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "header")
public class HeaderParameter extends AbstractParameter implements Parameter {

    public HeaderParameter() {
        super.setIn("header");
    }
}
