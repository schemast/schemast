package com.schemast.schemas;

public abstract class SchemaParser {
    public static final String HEADER = "header";
    public static final String NAMESPACE = "namespace";
    public static final String SCHEMA_NAME = "schemaName";
    public static final String VERSION = "version";
    public static final String FIELDS = "fields";

    public abstract Schema parse(String schema);

    public String getType() {
        SchemastParser anno = getClass().getAnnotation(SchemastParser.class);
        return anno.type();
    }

}
