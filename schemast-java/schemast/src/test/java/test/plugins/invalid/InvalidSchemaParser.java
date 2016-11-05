package test.plugins.invalid;

import com.schemast.parsers.SchemaParserBase;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;

@SchemastParser(
    type = ""
)
public class InvalidSchemaParser extends SchemaParserBase {

    public Schema parse(String schema) {
        return null;
    }

}
