package com.schemast.plugins.test1;

import com.schemast.parsers.ModelParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;
import test.TestData;

import java.util.Collection;
import java.util.Collections;

@SchemastParser(
    type = "TestModelParser1"
)
public class TestModelParser1 extends ModelParser {
    private TestData td = new TestData();

	@Override
    public Collection<Schema> parse(String model) {
        return Collections.singletonList(td.testSchema());
    }

    @Override
    public String getType() {
        return "TestModelParser1";
    }
}
