package com.schemast.plugins.test1;

import com.schemast.parsers.SchemaParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;
import test.TestData;

@SchemastParser(
    type = "TestSchemaParser1"
)
public class TestSchemaParser1 extends TestData implements SchemaParser {

    public Schema parse(String schema) {
        return testSchema();
    }

    @Override
    public String getType() {
        return "TestSchemaParser1";
    }
}
