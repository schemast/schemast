package com.schemast.models;

import com.schemast.util.Name;

public class Header {
    private Name namespace;

    public Header(String namespace) {
        this.namespace = new Name(namespace);
    }

    public String getNamespace() {
        return namespace.get();
    }
}
