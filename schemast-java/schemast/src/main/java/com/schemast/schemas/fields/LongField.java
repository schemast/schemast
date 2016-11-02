package com.schemast.schemas.fields;

public class LongField extends Field implements Defaulted<Long> {
    private Long defaultValue = null;

    public LongField(String name) {
        super(name);
    }

    public Field.Type getType() {
        return Type.LONG;
    }

    @Override
    public LongField withDefault(Long val) {
        this.defaultValue = val;
        return this;
    }

    @Override
    public Long getDefault() {
        return defaultValue;
    }

}
