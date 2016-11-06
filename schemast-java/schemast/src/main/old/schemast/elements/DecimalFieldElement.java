package com.schemast.schemas.elements.fields;

import com.schemast.schemas.elements.Element;
import com.schemast.schemas.elements.FieldElement;

public class DecimalFieldElement extends FieldElement {

    public static class Builder extends FieldBuilder {
        private Default<Double> def = new Default<>();
        private Default<Double> min = new Default<>();
        private Default<Double> max = new Default<>();
        
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

        public Builder withDefault(Double def) {
            this.def.set(def);
            return this;
        }

        public Builder minValue(Double min) {
            this.min.set(min);
            return this;
        }

        public Builder maxValue(Double max) {
            this.max.set(max);
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

            return new DecimalFieldElement(label, search, store, required, nullable, def, min, max);
        }

    }

    public static final String DEFAULT = "default";
    public static final String MIN_VALUE = "minValue";
    public static final String MAX_VALUE = "maxValue";

    private Default<Double> def;
    private Default<Double> min;
    private Default<Double> max;

    DecimalFieldElement(String label, Search search, Store store, boolean required, boolean nullable,
                               Default<Double> def, Default<Double> min, Default<Double> max) {
        super(label, FieldType.DECIMAL, search, store, required, nullable);
        this.def = def;
        this.min = min;
        this.max = max;
    }

    public Default<Double> getDefault() {
        return def;
    }

    public Default<Double> getMinValue() {
        return min;
    }

    public Default<Double> getMaxValue() {
        return max;
    }

}
