package com.schemast.schemas;

import test.TestData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MetadataTest extends TestData {

    private Metadata.Builder b;

    @Before
    public void doBefore() {
        b = new Metadata.Builder().name(NAME).namespace(NAMESPACE).version(VERSION);
    }

    @Test
    public void testValidMetadata() {
        Metadata m = b.build();
        assertEquals(NAME, m.getName());
        assertEquals(NAMESPACE, m.getNamespace());
        assertEquals(VERSION, m.getVersion());
    }

    @Test(expected = InvalidMetadataException.class)
    public void testNameCannotBeNull() {
        b.name(null).build();
    }

    @Test(expected = InvalidMetadataException.class)
    public void testNameCannotBeEmpty() {
        b.name("").build();
    }

    @Test(expected = InvalidMetadataException.class)
    public void testNamespaceCannotBeNull() {
        b.namespace(null).build();
    }

    @Test(expected = InvalidMetadataException.class)
    public void testNamespaceCannotBeEmpty() {
        b.namespace("").build();
    }

    @Test(expected = InvalidMetadataException.class)
    public void testVersionCannotBeNegative() {
        b.version(-1).build();
    }

    @Test(expected = InvalidMetadataException.class)
    public void testVersionCannotBeZero() {
        b.version(0).build();
    }
}
