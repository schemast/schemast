package com.schemast.schemas.elements;

public class MapElement extends Element {

    MapElement(String label) {
        super(label, Type.MAP);
    }

    public static class Builder extends ElementBuilder {

        public Builder(String label) {
            super(label);
        }

        @Override
        Element doBuild() {
            return new MapElement(label);
        }

    }

}
