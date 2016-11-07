package com.schemast.elements;

import java.util.Optional;

import static com.schemast.Constants.TYPE_BOOLEAN;

public class BooleanElement extends Element {
	public static final String DEFAULT = "default";

    public static class Builder extends ElementBuilder {
        private Boolean def;

        public Builder(String label) {
            super(label, TYPE_BOOLEAN);
        }

        @Override
        public Builder optional(Boolean optional) {
            super.optional(optional);
            return this;
        }

        public Builder withDefault(Boolean def) {
	        if (def == null) throw new InvalidElementException(DEFAULT + " cannot be null");
            this.def = def;
            return this;
        }

        @Override
        public BooleanElement build() {
            return new BooleanElement(name, type, optional, def);
        }

    }

    private Boolean def;

    private BooleanElement(String label, String type, boolean optional, Boolean def) {
        super(label, type, optional);
        this.def = def;
    }

    public Optional<Boolean> getDefault() {
        return Optional.ofNullable(def);
    }
}
