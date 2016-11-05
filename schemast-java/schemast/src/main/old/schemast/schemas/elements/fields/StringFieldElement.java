package com.schemast.schemas.elements.fields;

import com.schemast.schemas.elements.Element;
import com.schemast.schemas.elements.FieldElement;

public class StringFieldElement extends FieldElement {

    public static class Builder extends FieldBuilder {
        private Default<String> def = new Default<>();
        private Default<Long> minLength = new Default<>();
        private Default<Long> maxLength = new Default<>();

        public Builder(String label) {
            super(label);
        }

        @Override
        public Builder search(Search search) {
            super.search(search);
            return this;
        }

        @Override
        public Builder stored(Store store) {
            super.stored(store);
            return this;
        }

        @Override
        public Builder required(boolean required) {
            super.required(required);
            return this;
        }

        @Override
        public Builder nullable(boolean nullable) {
            super.nullable(nullable);
            return this;
        }

        public Builder withDefault(String def) {
            this.def.set(def);
            return this;
        }

        public Builder minLength(Long min) {
            this.minLength.set(min);
            return this;
        }

        public Builder maxLength(Long max) {
            this.maxLength.set(max);
            return this;
        }

        @Override
        protected Element doFieldBuild() {
            if (def.wasSet()) {
                if (!nullable && def.isNull()) {
                    throw new InvalidFieldException("Cannot set null " + DEFAULT + " when " + NULLABLE);
                } else {
                    if (minLength.isNotNull() && def.getValue().length() < minLength.getValue())
                        throw new InvalidFieldException(DEFAULT + " cannot be shorter than " + MIN_LENGTH);
                    if (maxLength.isNotNull() && def.getValue().length() > maxLength.getValue())
                        throw new InvalidFieldException(DEFAULT + " cannot be longer than " + MAX_LENGTH);
                }
            }

            if (minLength.wasSet()) {
                if (minLength.isNull()) {
                    throw new InvalidFieldException(MIN_LENGTH + " cannot be null");
                } else if (minLength.getValue() < 0L) {
                    throw new InvalidFieldException(MIN_LENGTH + " cannot be less than 0");
                }
            }

            if (maxLength.wasSet()) {
                if (maxLength.isNull()) {
                    throw new InvalidFieldException(MAX_LENGTH + " cannot be null");
                } else if (maxLength.getValue() < 1L) {
                    throw new InvalidFieldException(MAX_LENGTH + " cannot be less than 1");
                }
            }

            if (minLength.isNotNull() && maxLength.isNotNull() && minLength.getValue() > maxLength.getValue())
                throw new InvalidFieldException(MIN_LENGTH + " cannot be greater than " + MAX_LENGTH);

            return new StringFieldElement(label, search, store, required, nullable, def, minLength, maxLength);
        }

    }

    public static final String DEFAULT = "default";
    public static final String MIN_LENGTH = "minLength";
    public static final String MAX_LENGTH = "maxLength";

    private Default<Long> minLength;
    private Default<Long> maxLength;
    private Default<String> def;

    StringFieldElement(String label, Search search, Store store, boolean required, boolean nullable,
                              Default<String> def, Default<Long> minLength, Default<Long> maxLength) {
        super(label, FieldType.INTEGER, search, store, required, nullable);
        this.def = def;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public Default<String> getDefault() {
        return def;
    }

    public Default<Long> getMinLength() {
        return minLength;
    }
    
    public Default<Long> getMaxLength() {
        return maxLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringFieldElement)) return false;
        if (!super.equals(o)) return false;

        StringFieldElement that = (StringFieldElement) o;

        if (minLength != null ? !minLength.equals(that.minLength) : that.minLength != null) return false;
        if (maxLength != null ? !maxLength.equals(that.maxLength) : that.maxLength != null) return false;
        return def != null ? def.equals(that.def) : that.def == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (minLength != null ? minLength.hashCode() : 0);
        result = 31 * result + (maxLength != null ? maxLength.hashCode() : 0);
        result = 31 * result + (def != null ? def.hashCode() : 0);
        return result;
    }

}
