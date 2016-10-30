package com.schemast.schemas.fields;

public class UnknownFieldTypeException extends RuntimeException {

    public UnknownFieldTypeException(String name, String type) {
        super("The field " + name + " has an unknown type of " + type);
    }

}
