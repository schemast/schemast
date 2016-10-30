package com.schemast.schemas.fields;

public class StringField extends Field {
    private long minLength = 0;
    private long maxLength = Long.MAX_VALUE;
    private String defaultValue = null;

    public StringField(String name) {
        super(name);
    }

    public String getType() {
        return Field.STRING;
    }

    public StringField minLength(long min) {
        if (min < 0) throw new IllegalArgumentException("minLength cannot be less than 0");
        if (min > maxLength) throw new IllegalArgumentException("minLength cannot be greater than maxLength");
        this.minLength = min;
        return this;
    }

    public StringField maxLength(long max) {
        if (max < 0) throw new IllegalArgumentException("maxLength cannot be less than 0");
        if (max < minLength) throw new IllegalArgumentException("maxLength cannot be less than minLength");
        this.maxLength = max;
        return this;
    }

    public StringField withDefault(String val) {
        this.defaultValue = val;
        return this;
    }

}
