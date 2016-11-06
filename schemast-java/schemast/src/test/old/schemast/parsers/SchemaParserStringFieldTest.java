//package com.schemast.schemas.elements.fields;
//
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertTrue;
//
//public class SchemaParserStringFieldTest extends SchemaParserBaseFieldTest<String> {
//
//    @Override
//    protected StringElement newField(String fieldName) {
//        return new StringElement(fieldName).minLength(3).maxLength(4);
//    }
//
//    @Override
//    protected boolean testOutput(Field field) {
//        //TODO:
//        return true;
//    }
//
//    @Override
//    protected String goodDefaultValue() {
//        return "yay";
//    }
//
//    @Override
//    protected List<String> badDefaultValues() {
//        return Arrays.asList("no", "this is bad");
//    }
//
//    @Test
//    public void testSetDefaultThenHigherMinLength() {
//        StringElement f = new StringElement(FIELD_NAME).withDefault("good").minLength(2);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetDefaultThenEqualMinLength() {
//        StringElement f = new StringElement(FIELD_NAME).withDefault("good").minLength(4);
//        assertTrue(testOutput(f));
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetDefaultThenLowerMinLength() {
//        new StringElement(FIELD_NAME).withDefault("bad").minLength(4);
//    }
//
//    @Test
//    public void testSetDefaultThenHigherMaxLength() {
//        StringElement f = new StringElement(FIELD_NAME).withDefault("good").maxLength(5);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetDefaultThenEqualMaxLength() {
//        StringElement f = new StringElement(FIELD_NAME).withDefault("good").maxLength(4);
//        assertTrue(testOutput(f));
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetDefaultThenLowerMaxLength() {
//        new StringElement(FIELD_NAME).withDefault("bad").maxLength(2);
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetMinLengthBelowZero() {
//        new StringElement(FIELD_NAME).minLength(-1);
//    }
//
//    @Test
//    public void testSetMinLengthBelowMaxLength() {
//        StringElement f = new StringElement(FIELD_NAME).maxLength(5).minLength(3);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetMinLengthAtMaxLength() {
//        StringElement f = new StringElement(FIELD_NAME).maxLength(3).minLength(3);
//        assertTrue(testOutput(f));
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetMinLengthAboveMaxLength() {
//        new StringElement(FIELD_NAME).maxLength(5).minLength(7);
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetMaxLengthBelowMinLength() {
//        new StringElement(FIELD_NAME).minLength(5).maxLength(3);
//    }
//
//    @Test
//    public void testSetMaxLengthAtMinLength() {
//        StringElement f = new StringElement(FIELD_NAME).minLength(5).maxLength(5);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetMaxLengthAboveMinLength() {
//        StringElement f = new StringElement(FIELD_NAME).minLength(5).maxLength(7);
//        assertTrue(testOutput(f));
//    }
//
//}
