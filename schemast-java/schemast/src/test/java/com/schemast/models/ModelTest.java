package com.schemast.models;

import com.schemast.schemas.InvalidSchemaException;
import com.schemast.schemas.Schema;
import org.junit.Test;
import test.TestData;

import static org.junit.Assert.*;

public class ModelTest extends TestData {

    @Test
    public void testValidModel() {
        Model m = new Model.Builder().header(testHeader()).append(testSchema()).build();
        assertEquals(NAMESPACE, m.getHeader().getNamespace());
        assertEquals(1, m.getSchemas().size());
    }

    @Test(expected = InvalidModelException.class)
    public void testHeaderCannotBeNull() {
        new Model.Builder().header(null).append(testSchema()).build();
    }

    @Test(expected = InvalidModelException.class)
    public void testOneSchemaIsRequired() {
        new Model.Builder().header(testHeader()).build();
    }

    @Test
    public void testAppend() {
        Model m = new Model.Builder().header(testHeader())
                .append(testSchema())
                .append(testSchema(NAMESPACE, NAME2, VERSION2))
                .build();
        assertEquals(2, m.getSchemas().size());
    }

    @Test(expected = NullPointerException.class)
    public void testAppendNull() {
        new Model.Builder().header(testHeader()).append(null).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendDuplicate() {
        new Model.Builder().header(testHeader()).append(testSchema()).append(testSchema()).build();
    }
}
