package com.schemast.schemas;

import com.schemast.elements.Element;

import java.util.*;

public class Schema {
    public static final String METADATA = "metadata";
    public static final String STRUCTURE = "structure";

    private Metadata metadata;
    private Structure structure;

    public static class Builder {
        private Metadata metadata;
        private Structure structure = new Structure();

        public Builder metadata(Metadata metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder append(Element e) {
            structure.append(e);
            return this;
        }

        public Schema build() {
            if (metadata == null)
                throw new InvalidSchemaException(METADATA + " is required");
            if (structure.isEmpty())
                throw new InvalidSchemaException(STRUCTURE + " must have at least 1 element");
            else
                return new Schema(metadata, structure);
        }
    }

    Schema(Metadata metadata, Structure structure) {
        this.metadata = metadata;
        this.structure = structure;
    }

    public String getNamespace() {
        return metadata.getNamespace();
    }

    public String getName() {
        return metadata.getName();
    }

    public int getVersion() {
        return metadata.getVersion();
    }

    public Element find(String label) {
        return structure.find(label);
    }

    public Collection<Element> getAll() {
        return structure.getAll();
    }
}
