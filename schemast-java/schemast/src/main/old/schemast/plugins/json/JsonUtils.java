package com.schemast.elements.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.schemast.schemas.elements.fields.InvalidFieldException;

public class JsonUtils {

    public static String extractRequiredString(JsonNode n, String prop) {
        JsonNode node = n.get(prop);
        if (node == null) throw new InvalidFieldException(prop + " property is required");
        String name = node.asText();
        if (name == null || name.isEmpty()) throw new InvalidFieldException(prop + " property is required");
        return name;
    }

    public static int extractRequiredInt(JsonNode n, String prop) {
        JsonNode node = n.get(prop);
        if (node == null) throw new InvalidFieldException(prop + " property is required");
        if (!node.isInt()) throw new InvalidFieldException(prop + " property is required and must be an Integer");
        return node.asInt();
    }

    public static String extractOptionalString(JsonNode n, String field) {
        JsonNode strNode = n.get(field);
        if (strNode == null) return null;
        else return strNode.asText();
    }

    public static Boolean extractOptionalBoolean(JsonNode n, String field) {
        JsonNode boolNode = n.get(field);
        if (boolNode == null) return null;
        else return boolNode.asBoolean();
    }

}
