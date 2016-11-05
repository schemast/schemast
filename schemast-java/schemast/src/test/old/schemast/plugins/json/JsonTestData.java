package com.schemast.plugins.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.schemast.TestData;
import com.schemast.schemas.SchemaParser;
import com.schemast.schemas.SchemaParserBaseTest;
import com.schemast.schemas.elements.Element;

import java.io.IOException;
import java.util.List;

public class JsonTestData extends TestData {
    private ObjectMapper m = new ObjectMapper();

    public final String testData = buildShell(allFields);

    public String buildShell(List<Element> fields) {
        ObjectNode root = m.createObjectNode();
        ObjectNode header = root.putObject(SchemaParser.HEADER);
        header.put(SchemaParser.NAMESPACE, SchemaParserBaseTest.NAMESPACE);
        header.put(SchemaParser.SCHEMA_NAME, SchemaParserBaseTest.SCHEMA_NAME);
        header.put(SchemaParser.VERSION, SchemaParserBaseTest.VERSION);
        ArrayNode fieldsNode = root.putArray(SchemaParser.FIELDS);

        fields.forEach(f -> {
            ObjectNode n = fieldsNode.addObject();
            buildField(f, n);
        });

        return root.toString();
    }

    public void buildField(Element f, ObjectNode n) {
        //TODO:
//        n.put(Field.NAME, f.getName());
//        n.put(Field.TYPE, f.getType().toString());
//        n.put(Field.SEARCH, f.getSearch().toString());
//        n.put(Field.STORE, f.getStore().toString());
//        n.put(Field.REQUIRED, f.isRequired());
//        n.put(Field.NULLABLE, f.isNullable());
    }

    public JsonNode toJsonNode(ObjectNode n) {
        try {
            return m.readTree(n.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
