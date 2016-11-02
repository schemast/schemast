package com.schemast.schemas.fields;

public class DecimalField extends Field implements Defaulted<Double> {
    private Double defaultValue = null;

    public DecimalField(String name) {
        super(name);
    }

    public Field.Type getType() {
        return Type.DECIMAL;
    }

    @Override
    public DecimalField withDefault(Double val) {
        this.defaultValue = val;
        return this;
    }

    @Override
    public Double getDefault() {
        return defaultValue;
    }

}
