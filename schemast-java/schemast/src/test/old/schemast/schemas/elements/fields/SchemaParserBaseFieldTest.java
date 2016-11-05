//package com.schemast.schemas.elements.fields;
//
//import com.schemast.TestData;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public abstract class SchemaParserBaseFieldTest<T> {
//    protected String FIELD_NAME = "myField";
//
//    protected TestData data = new TestData();
//
//    protected abstract Field newField(String fieldName);
//
//    protected abstract boolean testOutput(Field field);
//
//    protected abstract T goodDefaultValue();
//
//    protected abstract List<T> badDefaultValues();
//
//    private T nullDefaultValue() {
//        return null;
//    }
//
//    @Test
//    public void testBasicFieldPermutations() {
//        data.buildAllBasicPermutations(newField(FIELD_NAME)).forEach(this::testOutput);
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        data.buildAllBasicPermutations(newField(FIELD_NAME)).forEach(f -> {
//            Field field = (Field) f.clone();
//            assertTrue(testOutput(field));
//            assertEquals(f, field);
//            assertEquals(f.hashCode(), field.hashCode());
//        });
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void testNullDefaultValueWhenNullable() {
//        Field f = newField(FIELD_NAME).nullable();
//        Field field = ((Defaulted<T>) f).withDefault(null);
//        assertNull(((Defaulted<T>) field).getDefault());
//        assertTrue(testOutput(field));
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    @SuppressWarnings("unchecked")
//    public void testNullDefaultValueWhenNotNullable() {
//        Field f = newField(FIELD_NAME).notNullable();
//        ((Defaulted<T>) f).withDefault(null);
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void testGoodDefaultValue() {
//        Field f = newField(FIELD_NAME);
//
//        T def = goodDefaultValue();
//        Field fieldWithDef = ((Defaulted<T>) f).withDefault(def);
//
//        assertTrue(testOutput(fieldWithDef));
//        assertEquals(def, ((Defaulted<T>) fieldWithDef).getDefault());
//
//        Field clone = (Field) fieldWithDef.clone();
//        assertEquals(fieldWithDef, clone);
//        assertEquals(fieldWithDef.hashCode(), clone.hashCode());
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void testBadDefaultValue() {
//        badDefaultValues().forEach(val -> {
//            Field f = newField(FIELD_NAME);
//            try {
//                ((Defaulted<T>) f).withDefault(val);
//                fail();
//            } catch (InvalidFieldException ife) {
//                // success
//            }
//        });
//    }
//
//}
