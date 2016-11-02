package com.schemast.schemas.elements;

public class FieldElement extends Element {

    FieldElement(String label) {
        super(label, Type.FIELD);
    }

    public static class Builder extends ElementBuilder {

        public Builder(String label) {
            super(label);
        }

        @Override
        public Element doBuild() {
            return new FieldElement(label);
        }

    }

}
