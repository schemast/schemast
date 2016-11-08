package com.schemast.plugins.test2;

import com.schemast.parsers.ModelParser;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;
import test.TestData;

import java.util.Collection;
import java.util.Collections;

@SchemastParser(
    type = "TestModelParser2"
)
public class TestModelParser2 extends ModelParser {
    private TestData td = new TestData();

    @Override
    public Collection<Schema> parse(String model) {
        return Collections.singletonList(td.testSchema());
    }

    @Override
    public String getType() {
        return "TestModelParser2";
    }
}
