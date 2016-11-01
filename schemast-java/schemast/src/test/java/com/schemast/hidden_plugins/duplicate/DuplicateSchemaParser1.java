package com.schemast.hidden_plugins.duplicate;

import com.schemast.schemas.Schema;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.SchemastParser;

@SchemastParser(
    type = "duplicate"
)
public class DuplicateSchemaParser1 extends SchemaParser {

    public Schema parse(String schema) {
        return null;
    }

}
