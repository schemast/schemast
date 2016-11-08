package com.schemast;

public class SchemastException extends RuntimeException {

    public SchemastException(String msg) {
        super(msg);
    }

    public SchemastException(Throwable t) {
        super(t);
    }
}
