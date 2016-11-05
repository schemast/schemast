package com.schemast.schemas;

import com.schemast.elements.Element;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Structure {
    private Map<String, Element> elements = new HashMap<>();

    public Structure append(Element e) {
        if (e == null) {
            throw new InvalidStructureException("Cannot add a null " + Element.ELEMENT);
        } else if (elements.putIfAbsent(e.getLabel(), e) != null) {
            throw new InvalidStructureException(
                    String.format("Duplicate %s '%s' found: %s", Element.ELEMENT, Element.LABEL, e.getLabel()));
        }

        return this;
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public Element find(String label) {
        return elements.get(label);
    }

    public Collection<Element> getAll() {
        return Collections.unmodifiableCollection(elements.values());
    }
}
