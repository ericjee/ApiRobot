package com.vip.yyl.service.utils.swagger.core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vip.yyl.domain.apis.swagger.RefResponse;
import com.vip.yyl.domain.apis.swagger.Response;

import java.io.IOException;

public class ResponseDeserializer extends JsonDeserializer<Response> {
    @Override
    public Response deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode sub = node.get("$ref");

        if (sub != null) {
            return Json.mapper().convertValue(node, RefResponse.class);
        } else {
            Response response = Json.responseMapper().convertValue(node, Response.class);
            return response;
        }
    }
}
