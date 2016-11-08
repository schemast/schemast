package com.schemast;

import com.schemast.parsers.ModelParser;
import com.schemast.parsers.ModelParserRegistry;
import com.schemast.repositories.InMemorySchemaRepository;
import com.schemast.repositories.SchemaRepository;
import com.schemast.schemas.*;

import java.util.Collection;

import static com.schemast.Constants.MODEL;

public class SchemastEngine {
    private final ModelParserRegistry registry;
    private final SchemaRepository repo;

    public SchemastEngine(SchemaRepository repo) {
        this(new ModelParserRegistry(), repo);
    }

	public SchemastEngine(ModelParserRegistry registry) {
		this.registry = registry;
		this.repo = new InMemorySchemaRepository();
	}

	public SchemastEngine(ModelParserRegistry registry, SchemaRepository repo) {
		this.registry = registry;
		this.repo = repo;
	}

    public void parse(String type, String model) {
        ModelParser sp = registry.getParser(type);

        if (sp == null) {
            String msg = String.format("No %s parser found for type %s", MODEL, type);
            throw new SchemastException(msg);
        } else {
	        Collection<Schema> schemas = sp.parse(model);

	        if (schemas == null) {
		        throw new SchemastException("No schemas found or schemas could not be parsed");
	        } else {
		        schemas.forEach(repo::put);
	        }
        }
    }
}
