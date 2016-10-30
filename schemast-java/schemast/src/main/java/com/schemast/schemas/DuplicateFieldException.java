package com.schemast.schemas;

public class DuplicateFieldException extends RuntimeException {

    public DuplicateFieldException(String fieldName, String base) {
        super("Duplicate field names are not allowed. Field: " + fieldName + ", Base: " + base);
    }
}
