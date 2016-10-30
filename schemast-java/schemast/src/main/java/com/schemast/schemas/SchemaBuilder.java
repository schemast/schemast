package com.schemast.schemas;

import com.schemast.plugins.json.JsonSchemaParser;
import com.schemast.plugins.logs.LogSchemaParser;
import com.schemast.plugins.properties.PropertiesSchemaParser;
import com.schemast.plugins.xsd.XsdSchemaParser;
import com.schemast.plugins.yml.YmlSchemaParser;

public class SchemaBuilder {
    private SchemaParser jsp = new JsonSchemaParser();
    private SchemaParser xsp = new XsdSchemaParser();
    private SchemaParser lsp = new LogSchemaParser();
    private SchemaParser ysp = new YmlSchemaParser();
    private SchemaParser psp = new PropertiesSchemaParser();

    public Schema parse(String type, String schema) {
        switch (type) {
            case "json":
                return validate(jsp.parse(schema));
            case "xsd":
                return validate(xsp.parse(schema));
            case "log":
                return validate(lsp.parse(schema));
            case "yml":
                return validate(ysp.parse(schema));
            case "properties":
                return validate(psp.parse(schema));
            default:
                throw new UnknownSchemaTypeException(type);
        }
    }

    private Schema validate(Schema s) {
        if (s == null) {
            throw new InvalidSchemaException("SchemaParser returned null");
        } else if (s.getName() == null || s.getName().isEmpty()) {
            throw new InvalidSchemaException("Schema name is required");
        } else if (s.getVersion() < 1) {
            throw new InvalidSchemaException("Schema version must be greater than 0");
        } else if (s.getFields().isEmpty()) {
            throw new InvalidSchemaException("Schemas require fields");
        } else {
            return s;
        }
    }

}
