package com.schemast;

import com.schemast.repositories.SchemaRepo;
import com.schemast.schemas.*;

public class SchemaEngine {
    private SchemaParserRegistry registry = new SchemaParserRegistry();
    private SchemaRepo repo;

    public SchemaEngine(SchemaRepo repo) {
        this.repo = repo;
    }

    public Schema parse(String type, String schema) {
        SchemaParser sp = registry.getParser(type);
        if (sp == null)
            return null;
        else
            return sp.parse(schema);
    }

}
