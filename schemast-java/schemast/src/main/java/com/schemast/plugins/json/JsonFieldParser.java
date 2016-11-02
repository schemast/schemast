package com.schemast.plugins.json;

import com.fasterxml.jackson.databind.JsonNode;

import com.schemast.schemas.fields.*;

public class JsonFieldParser {

    public Field parse(JsonNode n) {
        String name = JsonUtils.extractRequiredString(n, Field.NAME);
        String type = JsonUtils.extractRequiredString(n, Field.TYPE);
        Field field = createField(name, type);
        checkIndex(field, n);
        checkStore(field, n);
        checkRequired(field, n);
        checkNullable(field, n);
        return field;
    }

    private Field createField(String name, String type) {
        switch(Field.Type.getEnum(type)) {
            case BIG_DECIMAL: return new BigDecimalField(name);
            case BIG_INTEGER: return new BigIntegerField(name);
            case BOOLEAN: return new BooleanField(name);
            case DECIMAL: return new DecimalField(name);
            case FLOAT: return new FloatField(name);
            case INTEGER: return new IntegerField(name);
            case LONG: return new LongField(name);
            case STRING: return new StringField(name);
            default: throw new InvalidFieldException("Unknown " + Field.TYPE + " type: " + type);
        }
    }

    private void checkIndex(Field field, JsonNode n) {
        String index = JsonUtils.extractOptionalString(n, Field.SEARCH);
        if (index != null) {
            switch (Field.Search.getEnum(index)) {
                case MATCH: field.indexed(); break;
                case FULL: field.tokenized(); break;
                case NO: field.notIndexed(); break;
                default: throw new InvalidFieldException("Unknown " + Field.SEARCH + " setting: " + index);
            }
        }
    }

    private void checkStore(Field field, JsonNode n) {
        String store = JsonUtils.extractOptionalString(n, Field.STORE);
        if (store != null) {
            switch (Field.Store.getEnum(store)) {
                case YES: field.stored(); break;
                case NO: field.notStored(); break;
                default: throw new InvalidFieldException("Unknown " + Field.STORE + " setting: " + store);
            }
        }
    }

    private void checkRequired(Field field, JsonNode n) {
        Boolean req = JsonUtils.extractOptionalBoolean(n, Field.REQUIRED);
        if (req != null) {
            if (req) {
                field.required();
            } else {
                field.notRequired();
            }
        }
    }

    private void checkNullable(Field field, JsonNode n) {
        Boolean nu = JsonUtils.extractOptionalBoolean(n, Field.NULLABLE);
        if (nu != null) {
            if (nu) {
                field.nullable();
            } else {
                field.notNullable();
            }
        }
    }

}
