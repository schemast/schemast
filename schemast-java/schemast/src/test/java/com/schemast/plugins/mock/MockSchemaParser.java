package com.schemast.plugins.mock;

import com.schemast.SchemastParser;
import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.fields.StringField;

@SchemastParser(
    type = "mock",
    version = 12
)
public class MockSchemaParser extends SchemaParser {

    public Schema parse(String schema) {
        Schema s = new Schema("mock", 12);
        s.addField(new StringField("hello"));
        return s;
    }

}
