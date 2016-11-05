package test.plugins.duplicate;

import com.schemast.parsers.SchemaParserBase;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;

@SchemastParser(
    type = "duplicate"
)
public class DuplicateSchemaParser1 extends SchemaParserBase {

    public Schema parse(String schema) {
        return null;
    }

}
