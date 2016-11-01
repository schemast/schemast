package com.schemast.schemas;

import com.schemast.schemas.fields.StringField;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SchemaTest {
    private static final String NAMESPACE = "myNamespace";
    private static final String NAME = "mySchema";
    private static final int VERSION = 4;
    private static final String FIELD = "myField";

    private Schema.Builder b;

    @Before
    public void doBefore() {
        b = new Schema.Builder().name(NAME).namespace(NAMESPACE).version(VERSION).field(new StringField(FIELD));
    }

    @Test
    public void testDefaultIsAValidSchema() {
        Schema s = b.build();
        assertEquals(NAME, s.getName());
        assertEquals(NAMESPACE, s.getNamespace());
        assertEquals(VERSION, s.getVersion());
        assertEquals(FIELD, s.getField(FIELD).getName());
        assertEquals(1, s.getFields().size());
    }

    @Test(expected = InvalidSchemaException.class)
    public void testNameCannotBeNull() {
        b.name(null).build();
    }

    @Test(expected = InvalidSchemaException.class)
    public void testNameCannotBeEmpty() {
        b.name("").build();
    }

    @Test(expected = InvalidSchemaException.class)
    public void testNamespaceCannotBeNull() {
        b.namespace(null).build();
    }

    @Test(expected = InvalidSchemaException.class)
    public void testNamespaceCannotBeEmpty() {
        b.namespace("").build();
    }

    @Test
    public void testNamespaceDefault() {
        Schema s = new Schema.Builder().name(NAME).version(VERSION).field(new StringField(FIELD)).build();
        assertTrue(s.getNamespace().equals(Schema.DEFAULT_NAMESPACE));
    }

    @Test(expected = InvalidSchemaException.class)
    public void testVersionCannotBeNegative() {
        b.version(-1).build();
    }

    @Test(expected = InvalidSchemaException.class)
    public void testVersionCannotBeZero() {
        b.version(0).build();
    }

    @Test(expected = NullPointerException.class)
    public void testAddFieldFailsWhenFieldIsNull() {
        b.field(null).build();
    }

    @Test(expected = InvalidSchemaException.class)
    public void testAddFieldFailsWhenFieldIsAlreadyPresent() {
        b.field(new StringField(FIELD)).build();
    }

    @Test
    public void testMultipleFieldsCanBeAdded() {
        final String FIELD_1 = "field 1";
        final String FIELD_2 = "field 2";
        Schema s = b.field(new StringField(FIELD_1)).field(new StringField(FIELD_2)).build();

        assertEquals(FIELD, s.getField(FIELD).getName());
        assertEquals(FIELD_1, s.getField(FIELD_1).getName());
        assertEquals(FIELD_2, s.getField(FIELD_2).getName());
        assertEquals(3, s.getFields().size());
    }

}
