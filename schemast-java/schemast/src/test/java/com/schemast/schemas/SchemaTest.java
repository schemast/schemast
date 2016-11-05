package com.schemast.schemas;

import org.junit.Test;
import test.MockElement;
import test.TestData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SchemaTest extends TestData {

    @Test
    public void testValidSchema() {
        Schema s = new Schema.Builder()
                .metadata(testMetadata())
                .append(new MockElement(LABEL)).build();

        assertEquals(NAME, s.getName());
        assertEquals(NAMESPACE, s.getNamespace());
        assertEquals(VERSION, s.getVersion());

        assertNotNull(s.find(LABEL));
        assertEquals(1, s.getAll().size());
    }

    @Test(expected = InvalidSchemaException.class)
    public void testMetadataMustBeSet() {
        new Schema.Builder().append(new MockElement(LABEL)).build();
    }

    @Test(expected = InvalidSchemaException.class)
    public void testMetadataCannotBeNull() {
        new Schema.Builder().metadata(null).append(new MockElement(LABEL)).build();
    }

    @Test(expected = InvalidSchemaException.class)
    public void testAtLeastOneElementIsRequired() {
        new Schema.Builder().metadata(testMetadata()).build();
    }
}
