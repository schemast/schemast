package com.schemast.schemas;

import org.junit.Test;
import test.TestData;

import static org.junit.Assert.*;

public class SchemaTest extends TestData {

    @Test
    public void testValidSchema() {
        Schema s = new Schema(testHeader());

        assertEquals(NAME, s.getName());
        assertEquals(NAMESPACE, s.getNamespace());
        assertEquals(VERSION, s.getVersion());
    }

    @Test(expected = InvalidSchemaException.class)
    public void testHeaderCannotBeNull() {
        new Schema(null);
    }

    @Test
    public void testAppend() {
        Schema s = testSchema().append(mockElement(LABEL2));
        assertEquals(2, s.getStructure().size());
    }

    @Test(expected = InvalidSchemaException.class)
    public void testAppendNull() {
        testSchema().append(null);
    }

    @Test(expected = InvalidSchemaException.class)
    public void testAppendDuplicate() {
        testSchema().append(mockElement(LABEL)).append(mockElement(LABEL));
    }
}
