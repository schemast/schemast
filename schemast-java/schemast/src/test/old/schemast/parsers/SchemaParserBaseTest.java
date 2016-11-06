package com.schemast.schemas;

import com.schemast.schemas.elements.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class SchemaParserBaseTest {
    public static final String NAMESPACE = "myNamespace";
    public static final String SCHEMA_NAME = "mySchema";
    public static final int VERSION = 4;

    public static final String BOOLEAN_FIELD_NAME = "myBool";
    public static final String DECIMAL_FIELD_NAME = "myDecimal";
    public static final String INTEGER_FIELD_NAME = "myInt";
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
    public void testBooleanFieldElement() {
        Element f = s.getElement(BOOLEAN_FIELD_NAME);
        assertNotNull(f);
        assertEquals(BOOLEAN_FIELD_NAME, f.getLabel());
    }

    @Test
    public void testDecimalFieldElement() {
        Element f = s.getElement(DECIMAL_FIELD_NAME);
        assertNotNull(f);
        assertEquals(DECIMAL_FIELD_NAME, f.getLabel());
    }

    @Test
    public void testIntegerFieldElement() {
        Element f = s.getElement(INTEGER_FIELD_NAME);
        assertNotNull(f);
        assertEquals(INTEGER_FIELD_NAME, f.getLabel());
    }

    @Test
    public void testStringFieldElement() {
        Element f = s.getElement(STRING_FIELD_NAME);
        assertNotNull(f);
        assertEquals(STRING_FIELD_NAME, f.getLabel());
    }

}
