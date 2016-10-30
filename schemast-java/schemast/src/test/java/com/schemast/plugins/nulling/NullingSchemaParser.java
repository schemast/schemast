package com.schemast.plugins.nulling;

import com.schemast.SchemastParser;
import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;

@SchemastParser(
    type = "nulling",
    version = 12
)
public class NullingSchemaParser extends SchemaParser {

    public Schema parse(String schema) {
        return null;
    }

}
