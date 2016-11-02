package com.schemast.schemas.elements;

public class IdElement extends Element {

    IdElement(String label) {
        super(label, Type.ID);
    }

    public static class Builder extends ElementBuilder {

        public Builder(String label) {
            super(label);
        }

        @Override
        public Element doBuild() {
            return new IdElement(label);
        }

    }

}
