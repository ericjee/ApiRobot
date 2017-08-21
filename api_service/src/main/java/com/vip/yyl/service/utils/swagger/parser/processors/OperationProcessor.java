package com.vip.yyl.service.utils.swagger.parser.processors;

import com.vip.yyl.domain.apis.swagger.Operation;
import com.vip.yyl.domain.apis.swagger.RefResponse;
import com.vip.yyl.domain.apis.swagger.Response;
import com.vip.yyl.domain.apis.swagger.Swagger;
import com.vip.yyl.domain.apis.swagger.parameters.Parameter;
import com.vip.yyl.service.utils.swagger.parser.ResolverCache;

import java.util.List;
import java.util.Map;


public class OperationProcessor {
    private final ParameterProcessor parameterProcessor;
    private final ResponseProcessor responseProcessor;
    private final ResolverCache cache;

    public OperationProcessor(ResolverCache cache, Swagger swagger) {
        this.cache = cache;
        parameterProcessor = new ParameterProcessor(cache, swagger);
        responseProcessor = new ResponseProcessor(cache, swagger);
    }

    public void processOperation(Operation operation) {
        final List<Parameter> processedOperationParameters = parameterProcessor.processParameters(operation.getParameters());
        operation.setParameters(processedOperationParameters);

        final Map<String, Response> responses = operation.getResponses();

        if (responses != null) {
            for (String responseCode : responses.keySet()) {
                Response response = responses.get(responseCode);

                if(response != null) {
                    if (response instanceof RefResponse) {
                        RefResponse refResponse = (RefResponse) response;
                        Response resolvedResponse = cache.loadRef(refResponse.get$ref(), refResponse.getRefFormat(), Response.class);

                        if (resolvedResponse != null) {
                            response = resolvedResponse;
                            responses.put(responseCode, resolvedResponse);
                        }
                    }
                    responseProcessor.processResponse(response);
                }
            }
        }
    }
}
