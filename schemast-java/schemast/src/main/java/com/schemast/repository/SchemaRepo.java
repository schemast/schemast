package com.schemast.repository;

import com.schemast.schemas.Schema;

public interface SchemaRepo {
    void put(Schema schema);

    Schema get(String schemaName);
    Schema get(String schemaName, int version);

    boolean delete(String schemaName);
    boolean delete(String schemaName, int version);
}
