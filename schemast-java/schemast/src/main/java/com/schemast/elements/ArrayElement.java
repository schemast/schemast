package com.schemast.elements;

import java.util.*;

import static com.schemast.Constants.TYPE_ARRAY;

public class ArrayElement extends Element {
    public static final String MAX_SIZE = "maxSize";

    static class Builder extends ElementBuilder {
        private Integer maxSize;

        public Builder(String name) {
            super(name, TYPE_ARRAY);
        }

        @Override
        public Builder optional(Boolean optional) {
            super.optional(optional);
            return this;
        }

        public Builder maxSize(Integer maxSize) {
            if (maxSize == null) {
                throw new InvalidElementException(MAX_SIZE + " cannot be null");
            } else if (maxSize < 2) {
                throw new InvalidElementException(MAX_SIZE + " cannot be less than 2");
            } else {
                this.maxSize = maxSize;
                return this;
            }
        }

        @Override
        public ArrayElement build() {
            return new ArrayElement(name, type, optional, maxSize);
        }
    }

	private Integer maxSize;

    private ArrayElement(String name, String type, boolean optional, Integer maxSize) {
        super(name, type, optional);
        this.maxSize = maxSize;
    }

	public Optional<Integer> getMaxSize() {
		return Optional.ofNullable(maxSize);
    }
}
