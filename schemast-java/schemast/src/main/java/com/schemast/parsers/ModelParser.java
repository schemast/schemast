package com.schemast.parsers;

import com.schemast.schemas.Schema;

import java.util.Collection;

public abstract class ModelParser {
    private String type;

    public abstract Collection<Schema> parse(String model);

    public String getType() {
	    if (type == null)
	    	type = getClass().getAnnotation(SchemastParser.class).type();

        return type;
    }
}
