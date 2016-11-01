package com.schemast.plugins.mock;

import com.schemast.schemas.SchemastParser;
import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.fields.StringField;

@SchemastParser(
    type = "mock",
    version = 12
)
public class MockSchemaParser extends SchemaParser {

    public Schema parse(String schema) {
        return new Schema.Builder().name("mock").version(12).field(new StringField("hello")).build();
    }

}
