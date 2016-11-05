package com.schemast.schemas.elements.fields;

public class Default<T> {
    private T value = null;
    private boolean wasSet = false;

    public Default() {}

    public Default<T> set(T val) {
        this.value = val;
        this.wasSet = true;
        return this;
    }

    public T getValue() {
        return value;
    }

    public boolean wasSet() {
        return wasSet;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isNotNull() {
        return value != null;
    }

}
