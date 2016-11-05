package com.schemast.schemas.elements.fields;

import com.schemast.schemas.elements.Element;
import com.schemast.schemas.elements.FieldElement;

public class BooleanFieldElement extends FieldElement {

    public static class Builder extends FieldBuilder {
        Default<Boolean> def = new Default<>();

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

        public Builder withDefault(Boolean def) {
            this.def.set(def);
            return this;
        }

        @Override
        protected Element doFieldBuild() {
            if (!nullable && def.wasSet() && def.isNull())
                throw new InvalidFieldException(DEFAULT + " cannot be null when " + NULLABLE);

            return new BooleanFieldElement(label, search, store, required, nullable, def);
        }

    }

    public static final String DEFAULT = "default";

    private Default<Boolean> def;

    BooleanFieldElement(String label, Search search, Store store,
                        boolean required, boolean nullable, Default<Boolean> def) {
        super(label, FieldType.BOOLEAN, search, store, required, nullable);
        this.def = def;
    }

    public Default<Boolean> getDefault() {
        return def;
    }

}
