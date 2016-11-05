package com.schemast.parsers;

public abstract class SchemaParserBase implements SchemaParser {

    @Override
    public String getType() {
        return getClass().getAnnotation(SchemastParser.class).type();
    }

}
