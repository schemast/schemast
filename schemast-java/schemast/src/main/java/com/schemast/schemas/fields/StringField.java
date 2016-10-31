package com.schemast.schemas.fields;

public class StringField extends Field implements Defaulted<String> {
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
        if (min < 0) throw new InvalidFieldException("minLength cannot be less than 0");
        if (min > maxLength) throw new InvalidFieldException("minLength cannot be greater than maxLength");
        if (defaultValue != null && min > defaultValue.length()) throw new InvalidFieldException("minLength cannot be greater than length of default value");
        this.minLength = min;
        return this;
    }

    public StringField maxLength(long max) {
        if (max < 0) throw new InvalidFieldException("maxLength cannot be less than 0");
        if (max < minLength) throw new InvalidFieldException("maxLength cannot be less than minLength");
        if (defaultValue != null && max < defaultValue.length()) throw new InvalidFieldException("maxLength cannot be lower than length of default value");
        this.maxLength = max;
        return this;
    }

    @Override
    public StringField withDefault(String val) {
        if (val == null && !isNullable()) {
            throw new InvalidFieldException("Cannot set null default when field is not nullable");
        } else if (val != null && val.length() < minLength) {
            throw new InvalidFieldException("Default is shorter than minLength");
        } else if (val != null && val.length() > maxLength) {
            throw new InvalidFieldException("Default is longer than maxLength");
        }

        this.defaultValue = val;
        return this;
    }

    @Override
    public String getDefault() {
        return defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StringField that = (StringField) o;

        if (minLength != that.minLength) return false;
        if (maxLength != that.maxLength) return false;
        return defaultValue == null || defaultValue.equals(that.defaultValue);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (minLength ^ (minLength >>> 32));
        result = 31 * result + (int) (maxLength ^ (maxLength >>> 32));
        return (defaultValue == null) ? result : 31 * result + defaultValue.hashCode();
    }

}
