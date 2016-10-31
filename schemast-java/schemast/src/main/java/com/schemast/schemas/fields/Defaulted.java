package com.schemast.schemas.fields;

public interface Defaulted <T> {
    Field withDefault(T val);
    T getDefault();
}
