package com.schemast.plugins.test2;

import com.schemast.parsers.SchemaParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;
import test.TestData;

@SchemastParser(
    type = "TestSchemaParser2"
)
public class TestSchemaParser2 extends TestData implements SchemaParser {

    public Schema parse(String schema) {
        return testSchema();
    }

    @Override
    public String getType() {
        return "TestSchemaParser2";
    }
}
