package com.schemast.schemas.elements;

public class SubtypeElement extends Element {

    SubtypeElement(String label) {
        super(label, Type.SUBTYPE);
    }

    public static class Builder extends ElementBuilder {

        public Builder(String label) {
            super(label);
        }

        @Override
        Element doBuild() {
            return new SubtypeElement(label);
        }

    }

}
