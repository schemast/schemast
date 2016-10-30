package com.schemast.schemas;

import com.schemast.schemas.fields.StringField;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SchemaTest {
    private static final String SCHEMA_NAME = "mySchema";
    private static final int VERSION = 4;
    private static final String FIELD_NAME = "myField";
    private Schema s;

    @Before
    public void doBefore() {
        s = new Schema(SCHEMA_NAME, VERSION);
    }

    @Test(expected = InvalidSchemaException.class)
    public void testNameCannotBeNull() {
        new Schema(null, VERSION);
    }

    @Test(expected = InvalidSchemaException.class)
    public void testNameCannotBeEmpty() {
        new Schema("", VERSION);
    }

    @Test(expected = InvalidSchemaException.class)
    public void testVersionCannotBeNegative() {
        new Schema(SCHEMA_NAME, -1);
    }

    @Test(expected = InvalidSchemaException.class)
    public void testVersionCannotBeZero() {
        new Schema(SCHEMA_NAME, 0);
    }

    @Test
    public void testAddFieldWillAddAField() {
        s.addField(new StringField(FIELD_NAME));
        assertNotNull(s.getField(FIELD_NAME));
    }

    @Test(expected = NullPointerException.class)
    public void testAddFieldFailsWhenFieldIsNull() {
        s.addField(null);
    }

    @Test(expected = DuplicateFieldException.class)
    public void testAddFieldFailsWhenFieldIsAlreadyPresent() {
        s.addField(new StringField(FIELD_NAME));
        s.addField(new StringField(FIELD_NAME));
    }

}
