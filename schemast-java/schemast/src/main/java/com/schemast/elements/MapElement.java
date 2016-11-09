package com.schemast.elements;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.schemast.Constants.ELEMENT;
import static com.schemast.Constants.TYPE_MAP;

public class MapElement extends Element {

    static class Builder extends ElementBuilder {
        private Map<String, Element> elements = new HashMap<>();

        Builder(String name) {
            super(name, TYPE_MAP);
        }

        @Override
        public Builder optional(Boolean optional) {
            super.optional(optional);
            return this;
        }

        public Builder add(Element element) {
            if (element == null)
                throw new InvalidElementException("Cannot add a null " + ELEMENT + " to " + TYPE_MAP);
            if (elements.putIfAbsent(element.getName(), element) != null)
                throw new InvalidElementException("Duplicate " + ELEMENT + " in " + TYPE_MAP + " " + name);
            return this;
        }

        @Override
        public MapElement build() {
            if (elements.isEmpty())
                throw new InvalidElementException(TYPE_MAP + " requires at least one " + ELEMENT);
            else
                return new MapElement(name, type, optional, elements);
        }
    }

    private Map<String, Element> elements;

    private MapElement(String name, String type, boolean optional, Map<String, Element> elements) {
        super(name, type, optional);
        this.elements = elements;
    }

    public Collection<Element> getElements() {
        return elements.values();
    }
}
