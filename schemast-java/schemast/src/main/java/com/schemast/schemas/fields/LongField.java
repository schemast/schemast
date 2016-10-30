package com.schemast.schemas.fields;

public class LongField extends Field {
    private Integer defaultValue = null;

    public LongField(String name) {
        super(name);
    }

    public String getType() {
        return Field.LONG;
    }

    public LongField withDefault(int val) {
        this.defaultValue = val;
        return this;
    }

}
