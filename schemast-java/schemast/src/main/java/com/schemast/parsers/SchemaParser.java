package com.schemast.parsers;

import com.schemast.schemas.Schema;

public interface SchemaParser {

    Schema parse(String schema);

    String getType();
}
