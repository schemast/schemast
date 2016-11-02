package com.schemast.schemas.elements;

public class EnumElement extends Element {

    EnumElement(String label) {
        super(label, Type.ENUM);
    }

    public static class Builder extends ElementBuilder {

        public Builder(String label) {
            super(label);
        }

        @Override
        public Element doBuild() {
            return new EnumElement(label);
        }

    }

}
