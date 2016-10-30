package com.schemast.schemas;

public class UnknownSchemaTypeException extends RuntimeException {
    public UnknownSchemaTypeException(String type) {
        super("No builders found for schema type " + type);
    }
}
