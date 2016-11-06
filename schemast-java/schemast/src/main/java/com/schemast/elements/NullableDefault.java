package com.schemast.elements;

import java.util.NoSuchElementException;
import java.util.Optional;

public class NullableDefault<T> {
    private T value = null;
    private boolean wasSet = false;

    public NullableDefault() {}

    public NullableDefault<T> set(T val) {
        this.value = val;
        this.wasSet = true;
        return this;
    }

    public Optional<T> getOpt() {
        if (wasSet)
            return Optional.ofNullable(value);
        else
            throw new NoSuchElementException("Optional value was not set");
    }

    public boolean wasSet() {
        return wasSet;
    }
}
