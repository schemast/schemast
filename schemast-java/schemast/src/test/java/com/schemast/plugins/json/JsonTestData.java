package com.schemast.plugins.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.schemast.schemas.SchemaParser;
import com.schemast.plugins.SchemaParserBaseTest;
import com.schemast.schemas.fields.*;

import java.util.ArrayList;
import java.util.List;

public class JsonTestData {
    private ObjectMapper m = new ObjectMapper();

    public final List<Field> allFields = buildAllFields();
    public final String testData = buildShell(allFields);

    public String buildShell(List<Field> fields) {
        ObjectNode root = m.createObjectNode();
        ObjectNode header = root.putObject(SchemaParser.HEADER);
        header.put(SchemaParser.SCHEMA_NAME, SchemaParserBaseTest.SCHEMA_NAME);
        header.put(SchemaParser.VERSION, SchemaParserBaseTest.VERSION);
        ArrayNode fieldsNode = root.putArray(SchemaParser.FIELDS);

        fields.forEach(f -> {
            ObjectNode n = fieldsNode.addObject();
            n.put(Field.NAME, f.getName());
            n.put(Field.TYPE, f.getType());
            n.put(Field.INDEX, f.getIndex().toString());
            n.put(Field.STORE, f.getStore().toString());
            n.put(Field.REQUIRED, f.isRequired());
            n.put(Field.NULLABLE, f.isNullable());
        });

        return root.toString();
    }

    private List<Field> buildAllFields() {
        List<Field> fields = new ArrayList<>();
        fields.add(new BigDecimalField(SchemaParserBaseTest.BIG_DECIMAL_FIELD_NAME));
        fields.add(new BigIntegerField(SchemaParserBaseTest.BIG_INTEGER_FIELD_NAME));
        fields.add(new BooleanField(SchemaParserBaseTest.BOOLEAN_FIELD_NAME));
        fields.add(new DecimalField(SchemaParserBaseTest.DECIMAL_FIELD_NAME));
        fields.add(new FloatField(SchemaParserBaseTest.FLOAT_FIELD_NAME));
        fields.add(new IntegerField(SchemaParserBaseTest.INTEGER_FIELD_NAME));
        fields.add(new LongField(SchemaParserBaseTest.LONG_FIELD_NAME));
        fields.add(new StringField(SchemaParserBaseTest.STRING_FIELD_NAME));
        return fields;
    }

}
