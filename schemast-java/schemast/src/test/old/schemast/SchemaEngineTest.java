package com.schemast;

import com.schemast.schemas.SchemaParserRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SchemaEngineTest {
    private String UNKNOWN_PARSER = "huh?";
    private String MOCK_PARSER = "mock";
    private String EMPTY_PARSER = "empty";
    private String NULL_PARSER = "nulling";

    private SchemaEngine engine;

    @Before
    public void doBefore() {
        engine = new SchemaEngine(null);
    }

    @Test(expected = UnknownSchemaTypeException.class)
    public void testParsingUnknownSchema() {
        SchemaParserRegistry reg = new SchemaParserRegistry();
        assertNull(reg.getParser(UNKNOWN_PARSER));

        engine.parse(UNKNOWN_PARSER, "unknown data");
    }

    @Test
    public void testSchemaParserReturnsNullSchema() {
        SchemaParserRegistry reg = new SchemaParserRegistry();
        assertNotNull(reg.getParser(NULL_PARSER));

        engine.parse(NULL_PARSER, "huh?");
    }

    @Test
    public void testValidMockSchema() {
        SchemaParserRegistry reg = new SchemaParserRegistry();
        assertNotNull(reg.getParser(MOCK_PARSER));

        assertNotNull(engine.parse(MOCK_PARSER, "huh?"));
    }

}
