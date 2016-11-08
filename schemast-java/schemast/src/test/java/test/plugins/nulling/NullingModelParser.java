package test.plugins.nulling;

import com.schemast.parsers.ModelParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;

import java.util.Collection;

@SchemastParser(
    type = "nulling"
)
public class NullingModelParser extends ModelParser {

    @Override
    public Collection<Schema> parse(String model) {
        return null;
    }
}
