package com.schemast.schemas.elements.fields;

import com.schemast.schemas.elements.Element;
import com.schemast.schemas.elements.FieldElement;

public class IntFieldElement extends FieldElement {

    public static class Builder extends FieldBuilder {
        private Default<Integer> def = new Default<>();
        private Default<Integer> min = new Default<>();
        private Default<Integer> max = new Default<>();

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


        public Builder withDefault(Integer def) {
            this.def.set(def);
            return this;
        }

        public Builder minValue(Integer min) {
            this.min.set(min);
            return this;
        }

        public Builder maxValue(Integer max) {
            this.min.set(max);
            return this;
        }

        @Override
        protected Element doFieldBuild() {
            if (def.wasSet()) {
                if (!nullable && def.isNull()) {
                    throw new InvalidFieldException("Cannot set null " + DEFAULT + " when " + NULLABLE);
                } else {
                    if (min.isNotNull() && def.getValue() < min.getValue())
                        throw new InvalidFieldException(DEFAULT + " cannot be shorter than " + MIN_VALUE);
                    if (max.isNotNull() && def.getValue() > max.getValue())
                        throw new InvalidFieldException(DEFAULT + " cannot be longer than " + MAX_VALUE);
                }
            }

            if (min.wasSet()) {
                if (min.isNull()) {
                    throw new InvalidFieldException(MIN_VALUE + " cannot be null");
                } else if (min.getValue() < 0L) {
                    throw new InvalidFieldException(MIN_VALUE + " cannot be less than 0");
                }
            }

            if (max.wasSet()) {
                if (max.isNull()) {
                    throw new InvalidFieldException(MAX_VALUE + " cannot be null");
                } else if (max.getValue() < 1L) {
                    throw new InvalidFieldException(MAX_VALUE + " cannot be less than 1");
                }
            }

            if (min.isNotNull() && max.isNotNull() && min.getValue() > max.getValue())
                throw new InvalidFieldException(MIN_VALUE + " cannot be greater than " + MAX_VALUE);
            
            return new IntFieldElement(label, search, store, required, nullable, def, min, max);
        }

    }

    public static final String DEFAULT = "default";
    public static final String MIN_VALUE = "minValue";
    public static final String MAX_VALUE = "maxValue";

    private Default<Integer> def;
    private Default<Integer> min;
    private Default<Integer> max;

    IntFieldElement(String label, Search search, Store store, boolean required, boolean nullable,
                           Default<Integer> def, Default<Integer> min, Default<Integer> max) {
        super(label, FieldType.INTEGER, search, store, required, nullable);
        this.def = def;
        this.min = min;
        this.max = max;
    }

    public Default<Integer> getDefault() {
        return def;
    }

    public Default<Integer> getMinValue() {
        return min;
    }

    public Default<Integer> getMaxValue() {
        return max;
    }

}
