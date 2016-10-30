package com.schemast.schemas;

import com.schemast.schemas.fields.Field;
import com.schemast.schemas.fields.InvalidFieldException;

import java.util.HashMap;
import java.util.Map;

public class Schema {
    private String name;
    private int version;
    private Map<String, Field> fields = new HashMap<>();

    public Schema(String name, int version) {
        if (name == null || name.isEmpty()) throw new InvalidSchemaException("Schema name must be an identifying string");
        if (version < 1) throw new InvalidSchemaException("Schema version must be greater than 0");

        this.name = name;
        this.version = version;
    }

    public void addField(Field field) {
        if (field == null) throw new NullPointerException("Cannot add a null field");

        if (fields.putIfAbsent(field.getName(), field) != null) {
            throw new DuplicateFieldException(field.getName(), name);
        }
    }

    public Field getField(String fieldName) {
        return fields.get(fieldName);
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public Map<String,Field> getFields() {
        return fields;
    }
}
