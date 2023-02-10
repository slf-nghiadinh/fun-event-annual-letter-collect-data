package com.sunlife.vn.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static String extractJsonString(JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull()) {
            return null;
        }
        return jsonNode.asText();
    }
}
