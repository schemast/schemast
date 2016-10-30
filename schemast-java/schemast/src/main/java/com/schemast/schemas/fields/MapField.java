package com.schemast.schemas.fields;

import com.schemast.schemas.DuplicateFieldException;

import java.util.HashMap;
import java.util.Map;

public class MapField extends Field {
    private Map<String, Field> fields = new HashMap<>();

    public MapField(String name) {
        super(name);
    }

    public String getType() {
        return Field.MAP;
    }

    public MapField addField(Field field) {
        if (fields.putIfAbsent(field.getName(), field) != null) {
            throw new DuplicateFieldException(field.getName(), getName());
        }

        return this;
    }

}
