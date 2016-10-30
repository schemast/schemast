package com.schemast.schemas;

import com.schemast.plugins.json.JsonSchemaParser;

public class SchemaBuilder {
    private SchemaParser jsp = new JsonSchemaParser();

    public Schema parse(String type, String schema) {
        switch (type) {
            case "json":
                return validate(jsp.parse(schema));
            default:
                throw new UnknownSchemaTypeException(type);
        }
    }

    private Schema validate(Schema s) {
        if (s == null) {
            throw new InvalidSchemaException("SchemaParser returned null");
        } else if (s.getName() == null || s.getName().isEmpty()) {
            throw new InvalidSchemaException("Schema name is required");
        } else if (s.getVersion() < 1) {
            throw new InvalidSchemaException("Schema version must be greater than 0");
        } else if (s.getFields().isEmpty()) {
            throw new InvalidSchemaException("Schemas require fields");
        } else {
            return s;
        }
    }

}
