package com.schemast.plugins.empty;

import com.schemast.SchemastParser;
import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.fields.StringField;

@SchemastParser(
    type = "empty",
    version = 12
)
public class EmptySchemaParser extends SchemaParser {

    public Schema parse(String schema) {
        return new Schema("empty", 12);
    }

}
