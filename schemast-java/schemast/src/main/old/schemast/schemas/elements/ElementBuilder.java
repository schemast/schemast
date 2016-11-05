package com.schemast.schemas.elements;

import static com.schemast.schemas.elements.Element.LABEL;

public abstract class ElementBuilder {
    protected String label;

    protected ElementBuilder(String label) {
        this.label = label;
    }

    protected abstract Element doBuild();

    public Element build() {
        if (label == null || label.isEmpty()) {
            throw new InvalidElementException("Element " + LABEL + " is required");
        }

        return doBuild();
    }

}
