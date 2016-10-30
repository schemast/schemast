package com.schemast.schemas;

public interface SchemaParser {
    String HEADER = "header";
    String SCHEMA_NAME = "schemaName";
    String VERSION = "version";
    String FIELDS = "fields";

    Schema parse(String schema);
}
