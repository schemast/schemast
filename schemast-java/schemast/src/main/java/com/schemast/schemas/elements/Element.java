package com.schemast.schemas.elements;

public abstract class Element {

    public enum Type {
        ARRAY("array"),
        ENUM("enum"),
        ID("id"),
        MAP("map"),
        SUBTYPE("subtype"),
        FIELD("field");

        private final String name;

        private Type(final String name) {
            this.name = name;
        }

        public static Type getEnum(String value) {
            for (Type v : values()) {
                if (v.name.equalsIgnoreCase(value)) return v;
            }

            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static class Builder {
        private String label;

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public ElementBuilder type(Type type) {
            if (type == null) {
                throw new InvalidElementException("Element " + TYPE + "is required");
            } else {
                switch (type) {
                    case ARRAY: return new ArrayElement.Builder(label);
                    case ENUM: return new EnumElement.Builder(label);
                    case FIELD: return new FieldElement.Builder(label);
                    case ID: return new IdElement.Builder(label);
                    case MAP: return new MapElement.Builder(label);
                    case SUBTYPE: return new SubtypeElement.Builder(label);
                    default: throw new InvalidElementException("Element " + TYPE + "is unknown");
                }
            }
        }
    }

    public static final String LABEL = "label";
    public static final String TYPE = "type";

    private String label;
    private Type type;

    Element(String label, Type type) {
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public Type getType() {
        return type;
    }

}
