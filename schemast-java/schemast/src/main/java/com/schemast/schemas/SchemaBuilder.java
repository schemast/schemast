package com.schemast.schemas;

public class SchemaBuilder {
    private SchemaParserRegistry reg = new SchemaParserRegistry();

    public Schema parse(String type, String schema) {
        SchemaParser sp = reg.getParser(type);
        if (sp == null) throw new UnknownSchemaTypeException(type);
        return validate(sp.parse(schema));
    }

    private Schema validate(Schema s) {
        if (s == null) {
            throw new InvalidSchemaException("SchemaParser returned null");
        } else if (s.getFields().isEmpty()) {
            throw new InvalidSchemaException("Schemas require fields");
        } else {
            return s;
        }
    }

}
