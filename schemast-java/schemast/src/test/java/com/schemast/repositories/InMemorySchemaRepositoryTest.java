package com.schemast.repositories;

public class InMemorySchemaRepositoryTest extends SchemaRepositoryBaseTest {

    @Override
    protected SchemaRepository newInstance() {
        return new InMemorySchemaRepository();
    }
}
