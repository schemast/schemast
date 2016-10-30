package com.schemast.plugins.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schemast.schemas.Schema;
import com.schemast.schemas.fields.*;
import com.schemast.schemas.SchemaParseException;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.SchemastParser;
import java.io.IOException;

@SchemastParser(
    type = "json",
    version = 1
)
public class JsonSchemaParser extends SchemaParser {
    private JsonFieldParser fp = new JsonFieldParser();
    private ObjectMapper m = new ObjectMapper();

    public Schema parse(String schema) {
        try {
            JsonNode root = m.readTree(schema);
            Schema s = parseHeader(root.get(HEADER));
            parseFields(root.get(FIELDS), s);
            return s;
        } catch (IOException e) {
            throw new SchemaParseException(e);
        }
    }

    private Schema parseHeader(JsonNode header) throws IOException {
        if (header == null) throw new SchemaParseException(HEADER + " field is required");

        String name = JsonUtils.extractRequiredString(header, SCHEMA_NAME);
        int version = JsonUtils.extractRequiredInt(header, VERSION);
        return new Schema(name, version);
    }

    private void parseFields(JsonNode fields, Schema s) {
        if (fields == null) throw new SchemaParseException(FIELDS + " field is required");
        if (!fields.isArray()) throw new SchemaParseException(FIELDS + " element is an array");

        fields.iterator().forEachRemaining(f -> {
            Field field = fp.parse(f);
            s.addField(field);
        });
    }

}
