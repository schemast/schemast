package com.schemast.elements;

import com.schemast.util.Named;

public abstract class Element extends Named {

    public static class Builder {

	    public ArrayElement.Builder ofArray(String name) {
		    return new ArrayElement.Builder(name);
	    }

        public BooleanElement.Builder ofBoolean(String name) {
            return new BooleanElement.Builder(name);
        }

	    public IntElement.Builder ofInt(String name) {
		    return new IntElement.Builder(name);
	    }

        public MapElement.Builder ofMap(String name) {
            return new MapElement.Builder(name);
        }

        public StringElement.Builder ofString(String name) {
            return new StringElement.Builder(name);
        }

	    public EnumElement.Builder ofEnum(String name) {
		    return new EnumElement.Builder(name);
	    }
    }

    private String type;
    private boolean optional;

    protected Element(String name, String type, boolean optional) {
	    super(name);
        this.type = type;
        this.optional = optional;
    }

    public String getType() {
        return type;
    }

    public boolean isOptional() {
        return optional;
    }
}
