package com.schemast.schemas;

public class Header {
    public static final String NAMESPACE = "namespace";
    public static final String NAME = "name";
    public static final String VERSION = "version";
    public static final int MIN_VERSION = 1;

    private String namespace;
    private String name;
    private int version;

    public Header(String namespace, String name, int version) {
        if (namespace == null || namespace.isEmpty())
            throw new InvalidHeaderException(NAMESPACE + " must be a valid ofString");
        else if (name == null || name.isEmpty())
            throw new InvalidHeaderException(NAME + " must be an identifying ofString");
        else if (version < MIN_VERSION)
            throw new InvalidHeaderException(VERSION + " must be " + MIN_VERSION + " or greater");

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
