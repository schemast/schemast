package com.schemast.schemas.fields;

public class BooleanField extends Field {
    private Boolean defaultValue = null;

    public BooleanField(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return Field.BOOLEAN;
    }

    public BooleanField withDefault(boolean val) {
        this.defaultValue = val;
        return this;
    }

}
