package com.schemast.plugins.json;

import com.schemast.plugins.SchemaParserBaseTest;
import com.schemast.schemas.*;

public class JsonSchemaParserTest extends SchemaParserBaseTest {
    private JsonTestData data = new JsonTestData();
    private final String testData = data.testData;

    @Override
    protected Schema doParse() {
        SchemaParser p = new JsonSchemaParser();
        return p.parse(testData);
    }

}
