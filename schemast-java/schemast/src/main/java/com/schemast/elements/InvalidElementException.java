package com.schemast.elements;

import com.schemast.schemas.SchemaException;

public class InvalidElementException extends SchemaException {

    public InvalidElementException(String msg) {
        super(msg);
    }
}
