package com.schemast.elements;

import static com.schemast.elements.Element.*;

public abstract class ElementBuilder {
    protected String label;
    protected String type;
    protected boolean optional = false;

    protected ElementBuilder(String label, String type) {
        if (label == null || label.isEmpty()) {
            throw new InvalidElementException("Element " + LABEL + " is required");
        } else if (type == null || type.isEmpty()) {
            throw new InvalidElementException("Element " + TYPE + " is required");
        }

        this.label = label;
        this.type = type;
    }

    public ElementBuilder optional(Boolean optional) {
        if (optional == null) throw new InvalidElementException(OPTIONAL + " attribute cannot be null");
        this.optional = optional;
        return this;
    }

    public abstract Element build();
}
