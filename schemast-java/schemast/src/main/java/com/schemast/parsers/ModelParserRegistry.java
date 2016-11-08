package com.schemast.parsers;

import org.reflections.Reflections;

import java.util.*;

public class ModelParserRegistry {
    private final String NATIVE_REGISTRY = "com.schemast.plugins";
    private Map<String, ModelParser> parsers = new HashMap<>();

    public ModelParserRegistry() {
        this(new HashSet<>());
    }

    public ModelParserRegistry(String basePackage) {
        this(Collections.singletonList(basePackage));
    }

    public ModelParserRegistry(String[] basePackages) {
        this(Arrays.asList(basePackages));
    }

    public ModelParserRegistry(Collection<String> basePackages) {
        basePackages.forEach(this::reflectPackage);
        addPackage(NATIVE_REGISTRY);
    }

    public ModelParserRegistry addPackage(String pack) {
        if (pack == null || pack.isEmpty()) throw new ModelParserException("Cannot add a null or empty package");
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
            ModelParser sp = (ModelParser) o;
            String type = sp.getType();

            if (type == null || type.isEmpty()) {
                throw new ModelParserException(SchemastParser.class + " type must be a valid ofString");
            } else if (parsers.putIfAbsent(type, sp) != null) {
                throw new ModelParserException(
                        "Duplicate " + SchemastParser.class + " for type " + type + " found on the classpath");
            }
        } catch (Exception e) {
            throw new ModelParserException(e);
        }
    }

    public ModelParser getParser(String type) {
        if (type == null || type.isEmpty())
            throw new ModelParserException("Parser type must be a valid ofString");
        else
            return parsers.get(type);
    }
}
