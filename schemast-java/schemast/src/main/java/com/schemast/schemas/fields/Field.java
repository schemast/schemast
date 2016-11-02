package com.schemast.schemas.fields;

public abstract class Field implements Cloneable {

    public enum Type {
        ARRAY("array"),
        BIG_DECIMAL("bigDecimal"),
        BIG_INTEGER("bigInteger"),
        BOOLEAN("boolean"),
        DECIMAL("decimal"),
        FLOAT("float"),
        INTEGER("integer"),
        LONG("long"),
        MAP("map"),
        STRING("string");

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

    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String SEARCH = "search";
    public static final String STORE = "store";
    public static final String REQUIRED = "required";
    public static final String NULLABLE = "nullable";

    private String name;
    private Search search;
    private Store store;
    private boolean required;
    private boolean nullable;

    Field(String name) {
        if (name == null || name.isEmpty()) throw new InvalidFieldException("Field name must be an identifying string");

        this.name = name;
        this.search = Search.NO;
        this.store = Store.YES;
        this.required = false;
    }

    public abstract Type getType();

    public Field setSearch(Search search) {
        this.search = search;
        return this;
    }

    public Field notIndexed() {
        this.search = Search.NO;
        return this;
    }

    public Field indexed() {
        this.search = Search.MATCH;
        return this;
    }

    public Field tokenized() {
        this.search = Search.FULL;
        return this;
    }

    public Field setStored(Store store) {
        this.store = store;
        return this;
    }

    public Field stored() {
        this.store = Store.YES;
        return this;
    }

    public Field notStored() {
        this.store = Store.NO;
        return this;
    }

    public Field setRequired(boolean req) {
        this.required = req;
        return this;
    }

    public Field required() {
        this.required = true;
        return this;
    }

    public Field notRequired() {
        this.required = false;
        return this;
    }

    public Field setNullable(boolean nu) {
        this.nullable = nu;
        return this;
    }

    public Field nullable() {
        this.nullable = true;
        return this;
    }

    public Field notNullable() {
        this.nullable = false;
        return this;
    }

    public String getName() {
        return name;
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
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (required != field.required) return false;
        if (nullable != field.nullable) return false;
        if (!name.equals(field.name)) return false;
        if (search != field.search) return false;
        return store == field.store;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + search.hashCode();
        result = 31 * result + store.hashCode();
        result = 31 * result + (required ? 1 : 0);
        result = 31 * result + (nullable ? 1 : 0);
        return result;
    }

}
