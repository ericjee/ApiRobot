package com.vip.yyl.domain.parameters;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "body")
public class BodyParameter extends AbstractParameter implements Parameter {

    public BodyParameter() {
        super.setIn("body");
    }

}
