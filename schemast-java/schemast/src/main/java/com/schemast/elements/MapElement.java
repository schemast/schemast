package com.schemast.elements;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapElement extends Element {

    static class Builder extends ElementBuilder {
        private Map<String, Element> elements = new HashMap<>();

        Builder(String label) {
            super(label, TYPE_MAP);
        }

        @Override
        public Builder optional(Boolean optional) {
            super.optional(optional);
            return this;
        }

        public Builder add(Element element) {
            if (element == null)
                throw new InvalidElementException("Cannot add a null " + ELEMENT + " to " + TYPE_MAP);
            if (elements.putIfAbsent(element.getLabel(), element) != null)
                throw new InvalidElementException("Duplicate " + ELEMENT + " in " + TYPE_MAP + " " + label);
            return this;
        }

        @Override
        public MapElement build() {
            if (elements.isEmpty())
                throw new InvalidElementException(TYPE_MAP + " requires at least one " + ELEMENT);
            else
                return new MapElement(label, type, optional, elements);
        }
    }

    private Map<String, Element> elements;

    private MapElement(String label, String type, boolean optional, Map<String, Element> elements) {
        super(label, type, optional);
        this.elements = elements;
    }

    public Collection<Element> getElements() {
        return elements.values();
    }
}
