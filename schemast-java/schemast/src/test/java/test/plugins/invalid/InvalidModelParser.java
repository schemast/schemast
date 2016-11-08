package test.plugins.invalid;

import com.schemast.parsers.ModelParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;

import java.util.Collection;

@SchemastParser(
    type = ""
)
public class InvalidModelParser extends ModelParser {

    @Override
    public Collection<Schema> parse(String model) {
        return null;
    }
}
