package com.schemast.parsers;

public class ModelParserException extends RuntimeException {

    public ModelParserException(Throwable t) {
        super(t);
    }

    public ModelParserException(String msg) {
        super(msg);
    }
}
