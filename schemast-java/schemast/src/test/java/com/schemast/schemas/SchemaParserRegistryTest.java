package com.schemast.schemas;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SchemaParserRegistryTest {
    private SchemaParserRegistry registry;

    @Before
    public void doBefore() {
        registry = new SchemaParserRegistry();
    }

    @Test
    public void testGetParserWithUnknownSchemaType() {
        assertNull(registry.getParser("unknown"));
    }

    @Test
    public void testGetParserWithMockSchemaType() {
        assertNotNull(registry.getParser("mock"));
    }

}
