package test.plugins.duplicate;

import com.schemast.parsers.ModelParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;

import java.util.Collection;

@SchemastParser(
    type = "duplicate"
)
public class DuplicateModelParser1 extends ModelParser {

    @Override
    public Collection<Schema> parse(String model) {
        return null;
    }
}
