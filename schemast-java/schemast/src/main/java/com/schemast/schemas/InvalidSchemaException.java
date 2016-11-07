package com.schemast.schemas;

import com.schemast.SchemastException;

public class InvalidSchemaException extends SchemastException {

    public InvalidSchemaException(String msg) {
        super(msg);
    }
}
