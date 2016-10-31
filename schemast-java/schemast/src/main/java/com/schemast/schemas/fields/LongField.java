package com.schemast.schemas.fields;

public class LongField extends Field implements Defaulted<Long> {
    private Long defaultValue = null;

    public LongField(String name) {
        super(name);
    }

    public String getType() {
        return Field.LONG;
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
