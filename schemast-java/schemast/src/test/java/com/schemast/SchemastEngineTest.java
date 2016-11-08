package com.schemast;

import com.schemast.parsers.ModelParserRegistry;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SchemastEngineTest {
    private String UNKNOWN_PARSER = "huh?";
    private String MOCK_PARSER = "mock";
    private String EMPTY_PARSER = "empty";
    private String NULL_PARSER = "nulling";

	@Test
    public void testValidModel() {
        ModelParserRegistry reg = new ModelParserRegistry("test.plugins.mock");
        assertNotNull(reg.getParser(MOCK_PARSER));

		SchemastEngine engine = new SchemastEngine(reg);
		engine.parse(MOCK_PARSER, "good model");
        assertNotNull(reg.getParser(MOCK_PARSER));
    }

    @Test(expected = SchemastException.class)
    public void testParsingUnknownSchema() {
        ModelParserRegistry reg = new ModelParserRegistry();
        assertNull(reg.getParser(UNKNOWN_PARSER));

	    SchemastEngine engine = new SchemastEngine(reg);
        engine.parse(UNKNOWN_PARSER, "unknown data");
    }

    @Test(expected = SchemastException.class)
    public void testSchemaParserReturnsNullSchema() {
        ModelParserRegistry reg = new ModelParserRegistry("test.plugins.nulling");
        assertNotNull(reg.getParser(NULL_PARSER));

	    SchemastEngine engine = new SchemastEngine(reg);
        engine.parse(NULL_PARSER, "not really data");
    }
}
