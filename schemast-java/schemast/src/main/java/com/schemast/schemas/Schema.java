package com.schemast.schemas;

import com.schemast.elements.Element;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Schema {
    public static final String HEADER = "header";
    public static final String STRUCTURE = "structure";

    private Header header;
	private Map<String, Element> structure = new HashMap<>();

    public Schema(Header header) {
	    if (header == null) throw new InvalidSchemaException(HEADER + " cannot be null");
        this.header = header;
    }

	public Schema append(Element e) {
		if (e == null) {
			throw new InvalidSchemaException("Cannot add a null " + Element.ELEMENT);
		} else if (structure.putIfAbsent(e.getLabel(), e) != null) {
			throw new InvalidSchemaException(
					String.format("Duplicate %s '%s' found: %s", Element.ELEMENT, Element.LABEL, e.getLabel()));
		}

		return this;
	}

    public Header getHeader() {
        return header;
    }

    public String getNamespace() {
        return header.getNamespace();
    }

    public String getName() {
        return header.getName();
    }

    public int getVersion() {
        return header.getVersion();
    }

	public Collection<Element> getStructure() {
		return Collections.unmodifiableCollection(structure.values());
	}
}
