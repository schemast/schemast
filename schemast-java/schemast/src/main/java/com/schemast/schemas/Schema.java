package com.schemast.schemas;

import com.schemast.schemas.fields.Field;

import java.util.*;

public class Schema {
    public static final String DEFAULT_NAMESPACE = "default";

    private String namespace;
    private String name;
    private int version;
    private Map<String, Field> fields;

    public static class Builder {
        private String namespace = DEFAULT_NAMESPACE;
        private String name = null;
        private int version = -1;
        private Map<String, Field> fields = new HashMap<>();

        public Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder version(int version) {
            this.version = version;
            return this;
        }

        public Builder field(Field field) {
            if (field == null) {
                throw new NullPointerException("Cannot add a null field");
            } else if (fields.putIfAbsent(field.getName(), field) != null) {
                throw new InvalidSchemaException("Duplicate field name found: " + field.getName());
            } else {
                return this;
            }
        }

        public Schema build() {
            if (namespace == null || namespace.isEmpty()) throw new InvalidSchemaException("Schema namespace must be a valid string");
            if (name == null || name.isEmpty()) throw new InvalidSchemaException("Schema name must be an identifying string");
            if (version < 1) throw new InvalidSchemaException("Schema version must be greater than 0");
            if (fields.isEmpty()) throw new InvalidSchemaException("Schemas require one or more fields");

            return new Schema(namespace, name, version, Collections.unmodifiableMap(fields));
        }
    }

    Schema(String namespace, String name, int version, Map<String, Field> fields) {
        this.namespace = namespace;
        this.name = name;
        this.version = version;
        this.fields = fields;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public Field getField(String fieldName) {
        return fields.get(fieldName);
    }

    public Collection<Field> getFields() {
        return fields.values();
    }

}
