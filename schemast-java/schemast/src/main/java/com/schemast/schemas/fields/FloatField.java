package com.schemast.schemas.fields;

public class FloatField extends Field implements Defaulted<Float> {
    private Float defaultValue = null;

    public FloatField(String name) {
        super(name);
    }

    public String getType() {
        return Field.FLOAT;
    }

    @Override
    public FloatField withDefault(Float val) {
        this.defaultValue = val;
        return this;
    }

    @Override
    public Float getDefault() {
        return defaultValue;
    }
}
