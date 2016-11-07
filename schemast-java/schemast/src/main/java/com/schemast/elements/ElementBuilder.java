package com.schemast.elements;

import static com.schemast.Constants.OPTIONAL;
import static com.schemast.Constants.TYPE;

public abstract class ElementBuilder {
    protected String name;
    protected String type;
    protected boolean optional = false;

    protected ElementBuilder(String name, String type) {
        if (type == null || type.isEmpty()) {
            throw new InvalidElementException("Element " + TYPE + " is required");
        }

        this.name = name;
        this.type = type;
    }

    public ElementBuilder optional(Boolean optional) {
        if (optional == null) throw new InvalidElementException(OPTIONAL + " attribute cannot be null");
        this.optional = optional;
        return this;
    }

    public abstract Element build();
}
