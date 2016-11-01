package com.schemast.hidden_plugins.invalid;

import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.SchemastParser;

@SchemastParser(
    type = ""
)
public class InvalidSchemaParser extends SchemaParser {

    public Schema parse(String schema) {
        return null;
    }

}
