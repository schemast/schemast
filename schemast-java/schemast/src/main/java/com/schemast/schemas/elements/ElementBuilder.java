package com.schemast.schemas.elements;

import static com.schemast.schemas.elements.Element.LABEL;

public abstract class ElementBuilder {
    protected String label;

    ElementBuilder(String label) {
        this.label = label;
    }

    abstract Element doBuild();

    public Element build() {
        if (label == null || label.isEmpty()) {
            throw new InvalidElementException("Element " + LABEL + " is required");
        }

        return doBuild();
    }

}
