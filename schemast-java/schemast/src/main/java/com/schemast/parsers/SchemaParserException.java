package com.schemast.parsers;

public class SchemaParserException extends RuntimeException {

    public SchemaParserException(Throwable t) {
        super(t);
    }

    public SchemaParserException(String msg) {
        super(msg);
    }

}
