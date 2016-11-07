package com.schemast.elements;

import com.schemast.SchemastException;

public class InvalidElementException extends SchemastException {

    public InvalidElementException(String msg) {
        super(msg);
    }
}
