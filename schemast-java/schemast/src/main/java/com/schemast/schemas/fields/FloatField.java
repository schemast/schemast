package com.schemast.schemas.fields;

public class FloatField extends Field {
    private Float defaultValue = null;

    public FloatField(String name) {
        super(name);
    }

    public String getType() {
        return Field.FLOAT;
    }

    public FloatField withDefault(float val) {
        this.defaultValue = val;
        return this;
    }

}
