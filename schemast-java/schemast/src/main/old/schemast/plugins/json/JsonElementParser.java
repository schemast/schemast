package com.schemast.elements.json;

import com.fasterxml.jackson.databind.JsonNode;

import com.schemast.schemas.elements.Element;
import com.schemast.schemas.elements.FieldElement;
import com.schemast.schemas.elements.fields.*;

public class JsonElementParser {

    public Element parse(JsonNode n) {
        String name = JsonUtils.extractRequiredString(n, Element.LABEL);
        String type = JsonUtils.extractRequiredString(n, FieldElement.FIELD_TYPE);
        FieldBuilder fb = createElement(name, type);
        checkIndex(fb, n);
        checkStore(fb, n);
        checkRequired(fb, n);
        checkNullable(fb, n);
        return fb.build();
    }

    private FieldBuilder createElement(String label, String type) {
        FieldElement.FieldType ft = FieldElement.FieldType.getEnum(type);
        return new FieldElement.Builder(label).fieldType(ft);
    }

    private void checkIndex(FieldBuilder fb, JsonNode n) {
        String index = JsonUtils.extractOptionalString(n, FieldElement.SEARCH);
        if (index != null) {
            FieldElement.Search s = FieldElement.Search.getEnum(index);
            fb.search(s);
        }
    }

    private void checkStore(FieldBuilder fb, JsonNode n) {
        String store = JsonUtils.extractOptionalString(n, FieldElement.STORE);
        if (store != null) {
            FieldElement.Store s = FieldElement.Store.getEnum(store);
            fb.stored(s);
        }
    }

    private void checkRequired(FieldBuilder fb, JsonNode n) {
        Boolean req = JsonUtils.extractOptionalBoolean(n, FieldElement.REQUIRED);
        if (req != null) fb.required(req);
    }

    private void checkNullable(FieldBuilder fb, JsonNode n) {
        Boolean nu = JsonUtils.extractOptionalBoolean(n, FieldElement.NULLABLE);
        if (nu != null) fb.nullable(nu);
    }

}
