package com.schemast.repositories;

import com.schemast.schemas.Schema;

public interface SchemaRepository {
    void put(Schema schema);

    Schema get(String namespace, String name);
    Schema get(String namespace, String name, int version);

    boolean delete(String namespace);
    boolean delete(String namespace, String name);
    boolean delete(String namespace, String name, int version);
}
