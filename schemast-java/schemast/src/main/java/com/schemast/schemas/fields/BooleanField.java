package com.schemast.schemas.fields;

public class BooleanField extends Field implements Defaulted<Boolean> {
    private Boolean defaultValue = null;

    public BooleanField(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return Field.BOOLEAN;
    }

    @Override
    public BooleanField withDefault(Boolean val) {
        this.defaultValue = val;
        return this;
    }

    @Override
    public Boolean getDefault() {
        return defaultValue;
    }

}
