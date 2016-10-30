package com.schemast.schemas;

public class SchemaParseException extends RuntimeException {

    public SchemaParseException(Throwable t) {
        super(t);
    }

    public SchemaParseException(String msg) {
        super(msg);
    }

}
