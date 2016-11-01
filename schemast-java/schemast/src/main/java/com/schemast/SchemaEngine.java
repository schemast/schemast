package com.schemast;

import com.schemast.repository.SchemaRepo;
import com.schemast.schemas.*;

public class SchemaEngine {
    private SchemaParserRegistry registry = new SchemaParserRegistry();
    private SchemaRepo repo;

    public SchemaEngine(SchemaRepo repo) {
        this.repo = repo;
    }

    public Schema parse(String type, String schema) {
        SchemaParser sp = registry.getParser(type);
        if (sp == null) throw new UnknownSchemaTypeException(type);
        return sp.parse(schema);
    }

}
