package com.schemast.schemas;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SchemaParserRegistry {
    private Map<String, SchemaParser> parsers = new HashMap<>();

    public SchemaParserRegistry() {
        this("com.schemast.plugins");
    }

    public SchemaParserRegistry(String rootPackage) {
        Reflections reflections = new Reflections(rootPackage);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SchemastParser.class);

        annotated.forEach(c -> {
            try {
                Object o = c.newInstance();
                SchemaParser sp = (SchemaParser) o;
                String type = sp.getType();

                if (type == null || type.isEmpty()) {
                    throw new InvalidParserException("Type must be a valid string");
                } else if (parsers.putIfAbsent(type, sp) != null) {
                    throw new InvalidParserException("Duplicate " + SchemastParser.class + " for type " + type + " found on the classpath");
                }
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            } catch (ClassCastException cce) {
                throw new InvalidParserException("Class annotated with " + SchemastParser.class + "is not an instance of SchemaParser");
            }
        });
    }

    public SchemaParser getParser(String type) {
        return parsers.get(type);
    }

}
