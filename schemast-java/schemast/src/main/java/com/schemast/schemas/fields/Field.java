package com.schemast.schemas.fields;

public abstract class Field implements Cloneable {
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String INDEX = "index";
    public static final String STORE = "store";
    public static final String REQUIRED = "required";
    public static final String NULLABLE = "nullable";

    public static final String ARRAY = "array";
    public static final String BIG_DECIMAL = "bigDecimal";
    public static final String BIG_INTEGER = "bigInteger";
    public static final String BOOLEAN = "boolean";
    public static final String DECIMAL = "decimal";
    public static final String FLOAT = "float";
    public static final String INTEGER = "integer";
    public static final String LONG = "long";
    public static final String MAP = "map";
    public static final String STRING = "string";

    public enum Index { FULL, AS_IS, NO }
    public enum Store { YES, NO }

    private String name;
    private Index index;
    private Store store;
    private boolean required;
    private boolean nullable;

    Field(String name) {
        if (name == null || name.isEmpty()) throw new InvalidFieldException("Field name must be an identifying string");

        this.name = name;
        this.index = Index.NO;
        this.store = Store.YES;
        this.required = false;
    }

    public abstract String getType();

    public Field setIndex(Index i) {
        this.index = i;
        return this;
    }

    public Field notIndexed() {
        this.index = Index.NO;
        return this;
    }

    public Field indexed() {
        this.index = Index.AS_IS;
        return this;
    }

    public Field tokenized() {
        this.index = Index.FULL;
        return this;
    }

    public Field setStored(Store s) {
        this.store = s;
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

    public Index getIndex() {
        return index;
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
        if (index != field.index) return false;
        return store == field.store;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + index.hashCode();
        result = 31 * result + store.hashCode();
        result = 31 * result + (required ? 1 : 0);
        result = 31 * result + (nullable ? 1 : 0);
        return result;
    }

}
