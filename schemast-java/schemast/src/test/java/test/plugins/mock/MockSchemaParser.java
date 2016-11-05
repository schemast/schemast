package test.plugins.mock;

import com.schemast.parsers.SchemaParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;
import test.TestData;

@SchemastParser(
    type = "mock"
)
public class MockSchemaParser extends TestData implements SchemaParser {

    public Schema parse(String schema) {
        return testSchema();
    }

    @Override
    public String getType() {
        return "mock";
    }
}
