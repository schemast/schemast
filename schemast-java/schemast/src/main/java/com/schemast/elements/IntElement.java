package com.schemast.elements;

import java.util.Optional;

import static com.schemast.Constants.TYPE_INTEGER;

public class IntElement extends Element {
    public static final String DEFAULT = "default";
    public static final String MIN_VALUE = "minValue";
    public static final String MAX_VALUE = "maxValue";

    static class Builder extends ElementBuilder {
        private Integer def;
        private Integer minValue;
        private Integer maxValue;

        Builder(String label) {
            super(label, TYPE_INTEGER);
        }
        
        @Override
        public Builder optional(Boolean optional) {
            super.optional(optional);
            return this;
        }

        public Builder withDefault(Integer def) {
	        if (def == null) throw new InvalidElementException(DEFAULT + " cannot be null");
            this.def = def;
            return this;
        }

        public Builder minValue(Integer minValue) {
            if (minValue == null) {
                throw new InvalidElementException(MIN_VALUE + " cannot be null");
            } else {
                this.minValue = minValue;
                return this;
            }
        }

        public Builder maxValue(Integer maxValue) {
            if (maxValue == null) {
                throw new InvalidElementException(MAX_VALUE + " cannot be null");
            } else {
                this.maxValue = maxValue;
                return this;
            }
        }

        @Override
        public IntElement build() {
            if (def != null) {
                if (minValue != null && def < minValue) {
                    throw new InvalidElementException(DEFAULT + " cannot be less than " + MIN_VALUE);
                } else if (maxValue != null && def > maxValue) {
                    throw new InvalidElementException(DEFAULT + " cannot be greater than " + MAX_VALUE);
                }
            }

            if (minValue != null && maxValue != null && minValue > maxValue)
                throw new InvalidElementException(MIN_VALUE + " cannot be greater than " + MAX_VALUE);

            return new IntElement(name, type, optional, def, minValue, maxValue);
        }
    }

    private Integer def;
    private Integer min;
    private Integer max;

    private IntElement(String label, String type, boolean required, Integer def, Integer min, Integer max) {
        super(label, type, required);
        this.def = def;
        this.min = min;
        this.max = max;
    }

    public Optional<Integer> getDefault() {
        return Optional.ofNullable(def);
    }

    public Optional<Integer> getMinValue() {
        return Optional.ofNullable(min);
    }

    public Optional<Integer> getMaxValue() {
        return Optional.ofNullable(max);
    }
}
