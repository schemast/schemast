package com.schemast.schemas;

public class SchemaException extends RuntimeException {

    public SchemaException(String msg) {
        super(msg);
    }

    public SchemaException(Throwable t) {
        super(t);
    }
}
