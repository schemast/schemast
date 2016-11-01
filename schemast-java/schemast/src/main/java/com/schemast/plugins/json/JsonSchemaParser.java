package com.schemast.plugins.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schemast.schemas.Schema;
import com.schemast.schemas.fields.*;
import com.schemast.schemas.SchemaParseException;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.SchemastParser;
import java.io.IOException;

@SchemastParser(
    type = "json"
)
public class JsonSchemaParser extends SchemaParser {
    private JsonFieldParser fp = new JsonFieldParser();
    private ObjectMapper m = new ObjectMapper();

    public JsonSchemaParser() {
        m.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);
    }

    public Schema parse(String schema) {
        try {
            validateJson(schema);
            JsonNode root = m.readTree(schema);
            Schema.Builder b = parseHeader(root.get(HEADER));
            return parseFields(b, root.get(FIELDS));
        } catch (IOException e) {
            throw new SchemaParseException(e);
        }
    }

    private void validateJson(String schema) throws IOException {
        m.readTree(schema);
    }

    private Schema.Builder parseHeader(JsonNode header) throws IOException {
        Schema.Builder b = new Schema.Builder();
        b.namespace(JsonUtils.extractRequiredString(header, NAMESPACE));
        b.name(JsonUtils.extractRequiredString(header, SCHEMA_NAME));
        b.version(JsonUtils.extractRequiredInt(header, VERSION));
        return b;
    }

    private Schema parseFields(Schema.Builder b, JsonNode fields) {
        if (fields == null) throw new SchemaParseException(FIELDS + " element is required");
        if (!fields.isArray()) throw new SchemaParseException(FIELDS + " element must be an array");

        fields.iterator().forEachRemaining(f -> {
            Field field = fp.parse(f);
            b.field(field);
        });

        return b.build();
    }

}
