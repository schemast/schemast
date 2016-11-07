package com.schemast.models;

import com.schemast.SchemastException;

public class InvalidModelException extends SchemastException {

    public InvalidModelException(String msg) {
        super(msg);
    }
}
