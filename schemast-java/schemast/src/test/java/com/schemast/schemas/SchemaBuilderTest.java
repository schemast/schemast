package com.schemast.schemas;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SchemaBuilderTest {
    private String UNKNOWN_PARSER = "huh?";
    private String MOCK_PARSER = "mock";
    private String EMPTY_PARSER = "empty";
    private String NULL_PARSER = "nulling";

    private SchemaBuilder sb;

    @Before
    public void doBefore() {
        sb = new SchemaBuilder();
    }

    @Test(expected = UnknownSchemaTypeException.class)
    public void testParsingUnknownSchema() {
        SchemaParserRegistry reg = new SchemaParserRegistry();
        assertNull(reg.getParser(UNKNOWN_PARSER));

        sb.parse(UNKNOWN_PARSER, "unknown data");
    }

    @Test(expected = InvalidSchemaException.class)
    public void testSchemaWithNoFields() {
        SchemaParserRegistry reg = new SchemaParserRegistry();
        assertNotNull(reg.getParser(EMPTY_PARSER));

        sb.parse(EMPTY_PARSER, "empty data");
    }

    @Test(expected = InvalidSchemaException.class)
    public void testSchemaParserReturnsNullSchema() {
        SchemaParserRegistry reg = new SchemaParserRegistry();
        assertNotNull(reg.getParser(NULL_PARSER));

        sb.parse(NULL_PARSER, "huh?");
    }

    @Test
    public void testValidMockSchema() {
        SchemaParserRegistry reg = new SchemaParserRegistry();
        assertNotNull(reg.getParser(MOCK_PARSER));

        assertNotNull(sb.parse(MOCK_PARSER, "huh?"));
    }

}
