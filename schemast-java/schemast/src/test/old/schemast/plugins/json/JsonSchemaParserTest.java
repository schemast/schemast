//package com.schemast.plugins.json;
//
//import com.schemast.schemas.SchemaParserBaseTest;
//import com.schemast.schemas.*;
//import org.junit.Test;
//
//public class JsonSchemaParserTest extends SchemaParserBaseTest {
//    private JsonTestData data = new JsonTestData();
//    private final String testData = data.testData;
//
//    @Override
//    protected Schema doParse() {
//        SchemaParser p = new JsonSchemaParser();
//        return p.parse(testData);
//    }
//
//    @Test
//    public void testJsonMustBeValid() {
//        SchemaParser p = new JsonSchemaParser();
//        p.parse(data.testData + "}");
//        //TODO: This doesn't fail?!?!?
//    }
//
//    @Test(expected = SchemaParserException.class)
//    public void testFailOnDuplicateJsonFields() {
//        String data = "{\"" + SchemaParser.HEADER + "\":\"test1\",\"" + SchemaParser.HEADER + "\":\"test2\"}";
//        SchemaParser p = new JsonSchemaParser();
//        p.parse(data);
//    }
//
//}
