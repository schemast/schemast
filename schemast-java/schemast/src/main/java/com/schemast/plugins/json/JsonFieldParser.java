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
        switch(type) {
            case Field.BIG_DECIMAL: return new BigDecimalField(name);
            case Field.BIG_INTEGER: return new BigIntegerField(name);
            case Field.BOOLEAN: return new BooleanField(name);
            case Field.DECIMAL: return new DecimalField(name);
            case Field.FLOAT: return new FloatField(name);
            case Field.INTEGER: return new IntegerField(name);
            case Field.LONG: return new LongField(name);
            case Field.MAP: return new MapField(name);
            case Field.STRING: return new StringField(name);
            default: throw new UnknownFieldTypeException(name, type);
        }
    }

    private void checkIndex(Field field, JsonNode n) {
        String index = JsonUtils.extractOptionalString(n, Field.INDEX);
        if (index != null) {
            switch (Field.Index.valueOf(index)) {
                case AS_IS: field.indexed(); break;
                case FULL: field.tokenized(); break;
                case NO: field.notIndexed(); break;
                default: throw new InvalidFieldException("Unknown " + Field.INDEX + " setting: " + index);
            }
        }
    }

    private void checkStore(Field field, JsonNode n) {
        String store = JsonUtils.extractOptionalString(n, Field.STORE);
        if (store != null) {
            switch (Field.Store.valueOf(store)) {
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
