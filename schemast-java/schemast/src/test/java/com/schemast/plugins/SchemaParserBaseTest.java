package com.schemast.plugins;

import com.schemast.schemas.Schema;
import com.schemast.schemas.fields.Field;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class SchemaParserBaseTest {
    public static final String SCHEMA_NAME = "mySchema";
    public static final int VERSION = 4;
    public static final String BIG_DECIMAL_FIELD_NAME = "myBigDecimal";
    public static final String BIG_INTEGER_FIELD_NAME = "myBigInteger";
    public static final String BOOLEAN_FIELD_NAME = "myBool";
    public static final String DECIMAL_FIELD_NAME = "myDecimal";
    public static final String FLOAT_FIELD_NAME = "myFloat";
    public static final String INTEGER_FIELD_NAME = "myInt";
    public static final String LONG_FIELD_NAME = "myLong";
    public static final String STRING_FIELD_NAME = "myStr";

    protected Schema s;

    protected abstract Schema doParse();

    @Before
    public void doBefore() {
        s = doParse();
    }

    @Test
    public void testHeader() {
        assertEquals(SCHEMA_NAME, s.getName());
        assertEquals(VERSION, s.getVersion());
    }

    @Test
    public void testBigDecimalField() {
        Field f = s.getField(BIG_DECIMAL_FIELD_NAME);
        assertNotNull(f);
        assertEquals(BIG_DECIMAL_FIELD_NAME, f.getName());
    }

    @Test
    public void testBigIntegerField() {
        Field f = s.getField(BIG_INTEGER_FIELD_NAME);
        assertNotNull(f);
        assertEquals(BIG_INTEGER_FIELD_NAME, f.getName());
    }

    @Test
    public void testBooleanField() {
        Field f = s.getField(BOOLEAN_FIELD_NAME);
        assertNotNull(f);
        assertEquals(BOOLEAN_FIELD_NAME, f.getName());
    }

    @Test
    public void testDecimalField() {
        Field f = s.getField(DECIMAL_FIELD_NAME);
        assertNotNull(f);
        assertEquals(DECIMAL_FIELD_NAME, f.getName());
    }

    @Test
    public void testFloatField() {
        Field f = s.getField(FLOAT_FIELD_NAME);
        assertNotNull(f);
        assertEquals(FLOAT_FIELD_NAME, f.getName());
    }

    @Test
    public void testIntegerField() {
        Field f = s.getField(INTEGER_FIELD_NAME);
        assertNotNull(f);
        assertEquals(INTEGER_FIELD_NAME, f.getName());
    }

    @Test
    public void testLongField() {
        Field f = s.getField(LONG_FIELD_NAME);
        assertNotNull(f);
        assertEquals(LONG_FIELD_NAME, f.getName());
    }

    @Test
    public void testStringField() {
        Field f = s.getField(STRING_FIELD_NAME);
        assertNotNull(f);
        assertEquals(STRING_FIELD_NAME, f.getName());
    }

}
