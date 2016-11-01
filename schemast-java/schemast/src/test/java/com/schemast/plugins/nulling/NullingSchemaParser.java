package com.schemast.plugins.nulling;

import com.schemast.schemas.SchemastParser;
import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;

@SchemastParser(
    type = "nulling"
)
public class NullingSchemaParser extends SchemaParser {

    public Schema parse(String schema) {
        return null;
    }

}
