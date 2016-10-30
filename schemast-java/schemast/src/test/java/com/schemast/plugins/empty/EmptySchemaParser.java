package com.schemast.plugins.empty;

import com.schemast.schemas.SchemastParser;
import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;

@SchemastParser(
    type = "empty",
    version = 12
)
public class EmptySchemaParser extends SchemaParser {

    public Schema parse(String schema) {
        return new Schema("empty", 12);
    }

}
