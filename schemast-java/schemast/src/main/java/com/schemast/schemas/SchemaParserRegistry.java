package com.schemast.schemas;

import com.schemast.SchemastParser;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SchemaParserRegistry {
    private Map<String, SchemaParser> parsers = new HashMap<>();

    public SchemaParserRegistry() {
        Reflections reflections = new Reflections("com.schemast.plugins");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SchemastParser.class);

        annotated.forEach(c -> {
            try {
                Object o = c.newInstance();
                SchemaParser sp = (SchemaParser) o;

                if (parsers.putIfAbsent(sp.getType(), sp) != null) {
                    throw new RuntimeException("Duplicate " + SchemastParser.class + " for " + sp.getType() + " found on the classpath");
                }
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            } catch (ClassCastException cce) {
                throw new RuntimeException("Class annotated with " + SchemastParser.class + "is not an instance of SchemaParser");
            }
        });
    }

    public SchemaParser getParser(String type) {
        return parsers.get(type);
    }

}
