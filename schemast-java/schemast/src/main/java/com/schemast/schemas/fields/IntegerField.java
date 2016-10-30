package com.schemast.schemas.fields;

public class IntegerField extends Field {
    private Integer defaultValue = null;

    public IntegerField(String name) {
        super(name);
    }

    public String getType() {
        return Field.INTEGER;
    }

    public IntegerField withDefault(int val) {
        this.defaultValue = val;
        return this;
    }

}
