package com.schemast.schemas.fields;

public class DecimalField extends Field {
    private Double defaultValue = null;

    public DecimalField(String name) {
        super(name);
    }

    public String getType() {
        return Field.DECIMAL;
    }

    public DecimalField withDefault(double val) {
        this.defaultValue = val;
        return this;
    }

}
