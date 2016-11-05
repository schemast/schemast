package com.schemast.schemas.elements;

import com.schemast.schemas.elements.fields.*;

public class FieldElement extends Element implements Cloneable {

    public static class Builder extends ElementBuilder {
        private FieldBuilder fb;

        public Builder(String label) {
            super(label);
        }

        @Override
        protected Element doBuild() {
            if (fb == null)
                throw new InvalidElementException("FieldElement " + FIELD_TYPE + " is required");
            else
                return fb.build();
        }

        public FieldBuilder fieldType(FieldType fieldType) {
            if (fieldType == null) {
                throw new InvalidFieldException("FieldElement " + FIELD_TYPE + " cannot be null");
            } else {
                switch (fieldType) {
                    case BOOLEAN:
                        fb = new BooleanFieldElement.Builder(label);
                        return fb;
                    case DECIMAL:
                        fb = new DecimalFieldElement.Builder(label);
                        return fb;
                    case INTEGER:
                        fb = new IntFieldElement.Builder(label);
                        return fb;
                    case STRING:
                        fb = new StringFieldElement.Builder(label);
                        return fb;
                    default: throw new InvalidFieldException("Unknown " + FIELD_TYPE + ": " + fieldType);
                }
            }
        }

    }

    public enum FieldType {
        BOOLEAN("boolean"),
        DECIMAL("decimal"),
        INTEGER("int"),
        STRING("string");

        private final String name;

        private FieldType(final String name) {
            this.name = name;
        }

        public static FieldType getEnum(String value) {
            for (FieldType v : values()) {
                if (v.name.equalsIgnoreCase(value)) return v;
            }

            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Search {
        FULL("full"), MATCH("match"), NO("no");

        private String name;

        private Search(String name) {
            this.name = name;
        }

        public static Search getEnum(String value) {
            for (Search v : values()) {
                if (v.name.equalsIgnoreCase(value)) return v;
            }

            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Store {
        YES("yes"), NO("no");

        private String name;

        private Store(String name) {
            this.name = name;
        }

        public static Store getEnum(String value) {
            for (Store v : values()) {
                if (v.name.equalsIgnoreCase(value)) return v;
            }

            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static final String FIELD_TYPE = "fieldType";
    public static final String SEARCH = "search";
    public static final String STORE = "store";
    public static final String REQUIRED = "required";
    public static final String NULLABLE = "nullable";

    private FieldType fieldType;
    private Search search;
    private Store store;
    private boolean required;
    private boolean nullable;

    protected FieldElement(String label, FieldType fieldType, Search search,
                           Store store, boolean required, boolean nullable) {
        super(label, Element.Type.FIELD);
        this.fieldType = fieldType;
        this.search = search;
        this.store = store;
        this.required = required;
        this.nullable = nullable;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public Search getSearch() {
        return search;
    }

    public Store getStore() {
        return store;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isNullable() {
        return nullable;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldElement)) return false;

        FieldElement that = (FieldElement) o;

        if (required != that.required) return false;
        if (nullable != that.nullable) return false;
        if (fieldType != that.fieldType) return false;
        if (search != that.search) return false;
        return store == that.store;
    }

    @Override
    public int hashCode() {
        int result = fieldType != null ? fieldType.hashCode() : 0;
        result = 31 * result + (search != null ? search.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (required ? 1 : 0);
        result = 31 * result + (nullable ? 1 : 0);
        return result;
    }

}
