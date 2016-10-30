package com.schemast.schemas.fields;

public abstract class Field {
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

    public Field stored() {
        this.store = Store.YES;
        return this;
    }

    public Field notStored() {
        this.store = Store.NO;
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

}
