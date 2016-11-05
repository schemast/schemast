package com.schemast.plugins.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schemast.parsers.SchemaParserBase;
import com.schemast.parsers.SchemastParser;
import com.schemast.schemas.Schema;
import java.io.IOException;

@SchemastParser(
    type = "json"
)
public class JsonSchemaParser extends SchemaParserBase {

    @Override
    public Schema parse(String schema) {
        return null;
    }

//    private JsonElementParser fp = new JsonElementParser();
//    private ObjectMapper m = new ObjectMapper();
//
//    public JsonSchemaParser() {
//        m.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);
//    }
//
//    public Schema parse(String schema) {
//        try {
//            validateJson(schema);
//            JsonNode root = m.readTree(schema);
//            Schema.Builder b = parseHeader(root.get(HEADER));
//            return parseFields(b, root.get(FIELDS));
//        } catch (IOException e) {
//            throw new SchemaParseException(e);
//        }
//    }
//
//    private void validateJson(String schema) throws IOException {
//        m.readTree(schema);
//    }
//
//    private Schema.Builder parseHeader(JsonNode header) throws IOException {
//        Schema.Builder b = new Schema.Builder();
//        b.namespace(JsonUtils.extractRequiredString(header, NAMESPACE));
//        b.name(JsonUtils.extractRequiredString(header, SCHEMA_NAME));
//        b.version(JsonUtils.extractRequiredInt(header, VERSION));
//        return b;
//    }
//
//    private Schema parseFields(Schema.Builder b, JsonNode elements) {
//        if (elements == null) throw new SchemaParseException(FIELDS + " element is required");
//        if (!elements.isArray()) throw new SchemaParseException(FIELDS + " element must be an array");
//
//        elements.iterator().forEachRemaining(e -> {
//            Element el = fp.parse(e);
//            b.append(el);
//        });
//
//        return b.build();
//    }

}
