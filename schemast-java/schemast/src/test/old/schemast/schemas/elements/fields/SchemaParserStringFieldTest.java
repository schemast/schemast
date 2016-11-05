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
//    protected StringFieldElement newField(String fieldName) {
//        return new StringFieldElement(fieldName).minLength(3).maxLength(4);
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
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).withDefault("good").minLength(2);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetDefaultThenEqualMinLength() {
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).withDefault("good").minLength(4);
//        assertTrue(testOutput(f));
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetDefaultThenLowerMinLength() {
//        new StringFieldElement(FIELD_NAME).withDefault("bad").minLength(4);
//    }
//
//    @Test
//    public void testSetDefaultThenHigherMaxLength() {
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).withDefault("good").maxLength(5);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetDefaultThenEqualMaxLength() {
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).withDefault("good").maxLength(4);
//        assertTrue(testOutput(f));
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetDefaultThenLowerMaxLength() {
//        new StringFieldElement(FIELD_NAME).withDefault("bad").maxLength(2);
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetMinLengthBelowZero() {
//        new StringFieldElement(FIELD_NAME).minLength(-1);
//    }
//
//    @Test
//    public void testSetMinLengthBelowMaxLength() {
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).maxLength(5).minLength(3);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetMinLengthAtMaxLength() {
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).maxLength(3).minLength(3);
//        assertTrue(testOutput(f));
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetMinLengthAboveMaxLength() {
//        new StringFieldElement(FIELD_NAME).maxLength(5).minLength(7);
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testSetMaxLengthBelowMinLength() {
//        new StringFieldElement(FIELD_NAME).minLength(5).maxLength(3);
//    }
//
//    @Test
//    public void testSetMaxLengthAtMinLength() {
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).minLength(5).maxLength(5);
//        assertTrue(testOutput(f));
//    }
//
//    @Test
//    public void testSetMaxLengthAboveMinLength() {
//        StringFieldElement f = new StringFieldElement(FIELD_NAME).minLength(5).maxLength(7);
//        assertTrue(testOutput(f));
//    }
//
//}
