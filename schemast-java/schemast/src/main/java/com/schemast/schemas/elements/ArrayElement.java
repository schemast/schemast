package com.schemast.schemas.elements;

public class ArrayElement extends Element {

    ArrayElement(String label) {
        super(label, Type.ARRAY);
    }

    public static class Builder extends ElementBuilder {

        public Builder(String label) {
           super(label);
        }

        @Override
        public Element doBuild() {
            return new ArrayElement(label);
        }

    }

}
