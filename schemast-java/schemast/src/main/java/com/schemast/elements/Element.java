package com.schemast.elements;

public abstract class Element {
    public static final String ELEMENT = "element";
    public static final String ELEMENTS = "elements";
    public static final String LABEL = "label";
    public static final String TYPE = "type";
    public static final String OPTIONAL = "optional";

    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_INTEGER = "ofInt";
    public static final String TYPE_MAP = "ofMap";
    public static final String TYPE_STRING = "ofString";

    public static class Builder {

        public BooleanElement.Builder ofBoolean(String label) {
            return new BooleanElement.Builder(label);
        }

	    public IntElement.Builder ofInt(String label) {
		    return new IntElement.Builder(label);
	    }

        public MapElement.Builder ofMap(String label) {
            return new MapElement.Builder(label);
        }

        public StringElement.Builder ofString(String label) {
            return new StringElement.Builder(label);
        }
    }

    private String label;
    private String type;
    private boolean optional;

    protected Element(String label, String type, boolean optional) {
        this.label = label;
        this.type = type;
        this.optional = optional;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public boolean isOptional() {
        return optional;
    }
}
