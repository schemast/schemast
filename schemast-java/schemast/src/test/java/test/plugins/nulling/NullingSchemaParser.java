package test.plugins.nulling;

import com.schemast.parsers.SchemaParserBase;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;

@SchemastParser(
    type = "nulling"
)
public class NullingSchemaParser extends SchemaParserBase {

    public Schema parse(String schema) {
        return null;
    }

}
