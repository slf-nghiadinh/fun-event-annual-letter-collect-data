package com.sunlife.vn.common.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> objectToMap(Object obj) throws JsonProcessingException {
        if (obj instanceof String) {
            if (StringUtils.isEmpty(mapper.readTree(obj.toString()).asText())) {
                return mapper.convertValue(mapper.readTree(obj.toString()), new TypeReference<Map<String, Object>>() {
                });
            } else {
                return mapper.readValue(mapper.readTree(obj.toString()).asText(), new TypeReference<Map<String, Object>>() {
                });
            }
        } else {
            return mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
            });
        }
    }

    public static Map<String, String> stringToMap(String obj) throws JsonProcessingException {
        if (obj instanceof String) {
            if (StringUtils.isEmpty(mapper.readTree(obj.toString()).asText())) {
                return mapper.convertValue(mapper.readTree(obj.toString()), new TypeReference<Map<String, String>>() {
                });
            } else {
                return mapper.readValue(mapper.readTree(obj.toString()).asText(), new TypeReference<Map<String, String>>() {
                });
            }
        } else {
            return mapper.convertValue(obj, new TypeReference<Map<String, String>>() {
            });
        }
    }

    public static JsonNode objectToJsonNode(Object obj) throws JsonProcessingException {
        if (obj != null) {
            mapper.enable(Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            return mapper.readTree(obj.toString());
        }
        return mapper.createObjectNode();
    }

    public static ObjectNode getBlankObjectNode() {
        return mapper.createObjectNode();
    }

    public static String objectToJsonString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static boolean isJsonArray(Object obj) {
        JsonNode node;
        try {
            if (obj instanceof String) {
                node = mapper.convertValue(mapper.readTree(obj.toString()), JsonNode.class);
            } else {
                node = mapper.convertValue(obj, JsonNode.class);
            }
            if (node.isArray()) {
                return true;
            }
        } catch (IllegalArgumentException | JsonProcessingException e) {
            return false;
        }
        return false;
    }

	public static ArrayNode objectToArrayNode(Object obj) throws IOException {
		if (obj instanceof String) {
			return (ArrayNode) mapper.convertValue(mapper.readTree(obj.toString()), JsonNode.class);
		} else if (obj == null) {
			return (ArrayNode) objectToJsonNode("[]");
		} else {
			return (ArrayNode) mapper.convertValue(obj, JsonNode.class);
		}
	}

	public static List<String> arrayNodeStringToList(ArrayNode arrayNode) throws IOException {
		ObjectReader reader = mapper.readerFor(new TypeReference<List<String>>() {
		});
		return reader.readValue(arrayNode);
	}
	
    public static Map<String, Object> getFieldOfObjectAsMap(Object obj, String fieldName) throws JsonProcessingException {
        return JacksonUtil.objectToMap(JacksonUtil.objectToJsonNode(obj).get(fieldName));
    }

    public static String getFieldValueOfObject(String jsonStr, String fieldName) throws JsonProcessingException {
        ObjectNode node = new ObjectMapper().readValue(jsonStr, ObjectNode.class);
        if (node.has(fieldName)) {
            return node.get(fieldName).toString();
        } else {
            return "";
        }
    }

    public static ArrayNode getFieldOfObjectAsArrayNode(Object obj, String fieldName) throws IOException {
        return JacksonUtil.objectToArrayNode(JacksonUtil.objectToJsonNode(obj).get(fieldName));
    }
    
    public static Map<String, Object> jsonNodeToMap(JsonNode jsonNode) throws JsonMappingException, JsonProcessingException {
        return mapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
        });
    }

    public static JsonNode readJson(String jsonInString) {
        try {
            return mapper.readTree(jsonInString);
        } catch (Exception e) {
            return mapper.nullNode();
        }
    }
    
    public static  String mergeUserData(String userData1, String userData2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mapUserData1 = mapper.readValue(userData1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> mapUserData2 = mapper.readValue(userData2, new TypeReference<Map<String, Object>>() {
        });
        mapUserData1.putAll(mapUserData2);
        return mapper.writeValueAsString(mapUserData1);
    }
    
    public static String isNull(JsonNode input) {
        return input == null || input.isNull() ? null : input.textValue();
    }

}
