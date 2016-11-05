package com.schemast.schemas;

public class Metadata {
    public static final String NAMESPACE = "namespace";
    public static final String NAME = "name";
    public static final String VERSION = "version";
    public static final int MIN_VERSION = 1;

    private String namespace;
    private String name;
    private int version;

    public static class Builder {
        private String namespace;
        private String name;
        private int version;

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

        public Metadata build() {
            if (namespace == null || namespace.isEmpty())
                throw new InvalidMetadataException(NAMESPACE + " must be a valid string");
            else if (name == null || name.isEmpty())
                throw new InvalidMetadataException(NAME + " must be an identifying string");
            else if (version < MIN_VERSION)
                throw new InvalidMetadataException(VERSION + " must be at least " + MIN_VERSION);
            else
                return new Metadata(namespace, name, version);
        }
    }

    private Metadata(String namespace, String name, int version) {
        this.namespace = namespace;
        this.name = name;
        this.version = version;
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
}
