package com.schemast.schemas.fields;

public class IntegerField extends Field implements Defaulted<Integer> {
    private Integer defaultValue = null;

    public IntegerField(String name) {
        super(name);
    }

    public Field.Type getType() {
        return Type.INTEGER;
    }

    @Override
    public IntegerField withDefault(Integer val) {
        this.defaultValue = val;
        return this;
    }

    @Override
    public Integer getDefault() {
        return defaultValue;
    }

}
