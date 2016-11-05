package com.schemast.parsers;

import org.reflections.Reflections;

import java.util.*;

public class SchemaParserRegistry {
    private final String NATIVE_REGISTRY = "com.schemast.plugins";
    private Map<String, SchemaParser> parsers = new HashMap<>();

    public SchemaParserRegistry() {
        this(new HashSet<>());
    }

    public SchemaParserRegistry(String basePackage) {
        this(Arrays.asList(basePackage));
    }

    public SchemaParserRegistry(String[] basePackages) {
        this(Arrays.asList(basePackages));
    }

    public SchemaParserRegistry(Collection<String> basePackages) {
        basePackages.forEach(this::reflectPackage);
        addPackage(NATIVE_REGISTRY);
    }

    public SchemaParserRegistry addPackage(String pack) {
        reflectPackage(pack);
        return this;
    }

    private void reflectPackage(String pack) {
        Reflections reflections = new Reflections(pack);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SchemastParser.class);
        annotated.forEach(this::addToRegistry);
    }

    private void addToRegistry(Class<?> c) {
        try {
            Object o = c.newInstance();
            SchemaParser sp = (SchemaParser) o;
            String type = sp.getType();

            if (type == null || type.isEmpty()) {
                throw new SchemaParserException(SchemastParser.class + " type must be a valid string");
            } else if (parsers.putIfAbsent(type, sp) != null) {
                throw new SchemaParserException(
                        "Duplicate " + SchemastParser.class + " for type " + type + " found on the classpath");
            }
        } catch (Exception e) {
            throw new SchemaParserException(e);
        }
    }

    public SchemaParser getParser(String type) {
        if (type == null || type.isEmpty())
            throw new SchemaParserException("Parser type must be a valid string");
        else
            return parsers.get(type);
    }

}
