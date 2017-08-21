package com.vip.yyl.service.handler;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.vip.yyl.service.dto.ApiResponseDTO;
import com.vip.yyl.service.dto.AssertionDTO;
import com.vip.yyl.service.dto.BodyAssertDTO;
import com.vip.yyl.web.rest.AssertionResource;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AssertHandler {

    @Autowired
    private AssertionResource assertionResource;

    Logger logger = LoggerFactory.getLogger(AssertHandler.class);

    public void runAssert(AssertionDTO assertionDTO ,ApiResponseDTO apiResponseDTO, String nodeId) {
        if (assertionDTO == null)
            return ;

        List<BodyAssertDTO> bodyAssertDTOs = assertionDTO.getBodyAssertDTOs();
        if (bodyAssertDTOs == null)
            return ;
        try {
            BodyAssertTool tool = new BodyAssertTool(apiResponseDTO.getBody());
            for (BodyAssertDTO bodyAssertDTO : bodyAssertDTOs) {
                tool.doAssert(bodyAssertDTO);
            }
            assertionResource.update(nodeId, assertionDTO);
        } catch (Exception e) {
            logger.debug("Body is not json");
        }
    }


    public AssertionDTO runAssertTry(AssertionDTO assertionDTO ,ApiResponseDTO apiResponseDTO, String nodeId) {
        if (assertionDTO == null)
            return null;

        List<BodyAssertDTO> bodyAssertDTOs = assertionDTO.getBodyAssertDTOs();
        if (bodyAssertDTOs == null)
            return null;
        try {
            BodyAssertTool tool = new BodyAssertTool(apiResponseDTO.getBody());
            for (BodyAssertDTO bodyAssertDTO : bodyAssertDTOs) {
                tool.doAssert(bodyAssertDTO);
            }
            assertionResource.update(nodeId, assertionDTO);
        } catch (Exception e) {
            logger.debug("Body is not json");
        }
        return assertionDTO;
    }

    private class BodyAssertTool {
        Object body;

        public BodyAssertTool(String body) {
            this.body = Configuration.defaultConfiguration().jsonProvider().parse(body);
        }

        @SuppressWarnings("unchecked")
        public void doAssert(BodyAssertDTO bodyAssertDTO) {
            String propertyName = bodyAssertDTO.getPropertyName();
            if (propertyName.startsWith("[")) {
                propertyName = "$" + propertyName;
            } else {
                propertyName = "$." + propertyName;
            }

            boolean success = false;

            try {
                Object actualValue = JsonPath.read(body, propertyName);
                bodyAssertDTO.setActualValue(actualValue.toString());
                if (actualValue instanceof Number) {
                    success = evaluate(bodyAssertDTO.getExpectedValue(), bodyAssertDTO.getComparator(), (Number) actualValue);
                } else if (actualValue instanceof String) {
                    success = evaluate(bodyAssertDTO.getExpectedValue(), bodyAssertDTO.getComparator(), (String) actualValue);
                } else if (actualValue instanceof Map) {
                    JSONObject json = new JSONObject((Map<String, ?>) actualValue);
                    bodyAssertDTO.setActualValue(json.toString());
                    success = evaluate(bodyAssertDTO.getExpectedValue(), bodyAssertDTO.getComparator(), json);
                } else if (actualValue instanceof List) {
                    success = evaluate(bodyAssertDTO.getExpectedValue(), bodyAssertDTO.getComparator(), (List<Object>) actualValue);
                }
            } catch (Exception e) {
                logger.debug("propery not found");
            }

            bodyAssertDTO.setSuccess(success);
        }

        private boolean evaluate(String expectedValue, String comparator, String actualValue) {
            boolean result = false;

            switch (comparator) {
                case "=":
                    result = actualValue.equals(expectedValue);
                    break;
                case "!=":
                    result = !actualValue.equals(expectedValue);
                    break;
                case "Contains":
                    result = actualValue.contains(expectedValue);
                    break;
                case "! Contains":
                    result = !actualValue.contains(expectedValue);
                    break;
            }

            return result;
        }

        private boolean evaluate(String expectedValue, String comparator, Number actualValue) {
            boolean result = false;

            double expected = Double.parseDouble(expectedValue);
            double actual = actualValue.doubleValue();

            switch (comparator) {
                case "=":
                    result = actual == expected;
                    break;
                case "!=":
                    result = actual != expected;
                    break;
                case "<":
                    result = actual < expected;
                    break;
                case "<=":
                    result = actual <= expected;
                    break;
                case ">":
                    result = actual > expected;
                    break;
                case ">=":
                    result = actual >= expected;
                    break;
            }

            return result;
        }

        private boolean evaluate(String expectedValue, String comparator, JSONObject actualValue) {
            boolean result = false;

            switch (comparator) {
                case "Contains Key":
                    result = actualValue.containsKey(expectedValue);
                    break;
                case "! Contains Key":
                    result = !actualValue.containsKey(expectedValue);
                    break;
                case "Contains Value":
                    result = actualValue.containsValue(expectedValue);
                    ;
                    break;
                case "! Contains Value":
                    result = !actualValue.containsValue(expectedValue);
                    break;

            }

            return result;
        }

        private boolean evaluate(String expectedValue, String comparator, List<Object> actualValue) {
            boolean result = false;

            switch (comparator) {
                case "Contains Value":
                    result = actualValue.contains(expectedValue);
                    break;
                case "! Contains Value":
                    result = !actualValue.contains(expectedValue);
                    break;

            }
            return result;
        }
    }
}
