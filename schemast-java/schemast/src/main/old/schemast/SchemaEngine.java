package com.schemast;

import com.schemast.parsers.SchemaParser;
import com.schemast.parsers.SchemaParserRegistry;
import com.schemast.repositories.SchemaRepository;
import com.schemast.schemas.*;

public class SchemaEngine {
    private SchemaParserRegistry registry = new SchemaParserRegistry();
    private SchemaRepository repo;

    public SchemaEngine(SchemaRepository repo) {
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
