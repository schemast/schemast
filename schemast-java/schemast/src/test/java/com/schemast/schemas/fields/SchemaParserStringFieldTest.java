package com.schemast.schemas.fields;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SchemaParserStringFieldTest extends SchemaParserBaseFieldTest<String> {

    @Override
    protected StringField newField(String fieldName) {
        return new StringField(fieldName).minLength(3).maxLength(4);
    }

    @Override
    protected boolean testOutput(Field field) {
        //TODO:
        return true;
    }

    @Override
    protected String goodDefaultValue() {
        return "yay";
    }

    @Override
    protected List<String> badDefaultValues() {
        return Arrays.asList("no", "this is bad");
    }

    @Test
    public void testSetDefaultThenHigherMinLength() {
        StringField f = new StringField(FIELD_NAME).withDefault("good").minLength(2);
        assertTrue(testOutput(f));
    }

    @Test
    public void testSetDefaultThenEqualMinLength() {
        StringField f = new StringField(FIELD_NAME).withDefault("good").minLength(4);
        assertTrue(testOutput(f));
    }

    @Test(expected = InvalidFieldException.class)
    public void testSetDefaultThenLowerMinLength() {
        new StringField(FIELD_NAME).withDefault("bad").minLength(4);
    }

    @Test
    public void testSetDefaultThenHigherMaxLength() {
        StringField f = new StringField(FIELD_NAME).withDefault("good").maxLength(5);
        assertTrue(testOutput(f));
    }

    @Test
    public void testSetDefaultThenEqualMaxLength() {
        StringField f = new StringField(FIELD_NAME).withDefault("good").maxLength(4);
        assertTrue(testOutput(f));
    }

    @Test(expected = InvalidFieldException.class)
    public void testSetDefaultThenLowerMaxLength() {
        new StringField(FIELD_NAME).withDefault("bad").maxLength(2);
    }

    @Test(expected = InvalidFieldException.class)
    public void testSetMinLengthBelowZero() {
        new StringField(FIELD_NAME).minLength(-1);
    }

    @Test
    public void testSetMinLengthBelowMaxLength() {
        StringField f = new StringField(FIELD_NAME).maxLength(5).minLength(3);
        assertTrue(testOutput(f));
    }

    @Test
    public void testSetMinLengthAtMaxLength() {
        StringField f = new StringField(FIELD_NAME).maxLength(3).minLength(3);
        assertTrue(testOutput(f));
    }

    @Test(expected = InvalidFieldException.class)
    public void testSetMinLengthAboveMaxLength() {
        new StringField(FIELD_NAME).maxLength(5).minLength(7);
    }

    @Test(expected = InvalidFieldException.class)
    public void testSetMaxLengthBelowMinLength() {
        new StringField(FIELD_NAME).minLength(5).maxLength(3);
    }

    @Test
    public void testSetMaxLengthAtMinLength() {
        StringField f = new StringField(FIELD_NAME).minLength(5).maxLength(5);
        assertTrue(testOutput(f));
    }

    @Test
    public void testSetMaxLengthAboveMinLength() {
        StringField f = new StringField(FIELD_NAME).minLength(5).maxLength(7);
        assertTrue(testOutput(f));
    }

}
