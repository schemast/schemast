//package com.schemast.schemas.elements.fields;
//
//import com.schemast.schemas.elements.Element;
//import com.schemast.schemas.elements.FieldElement;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//public class FieldBuilderTest {
//
//    private class MyFieldBuilder extends FieldBuilder {
//        @Override
//        protected Element doFieldBuild() {
//            return null;
//        }
//    }
//
//    private FieldElement f;
//
//    @Before
//    public void doBefore() {
//        f = new MyField("myField");
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testNameCannotBeNull() {
//        new MyField(null);
//    }
//
//    @Test(expected = InvalidFieldException.class)
//    public void testNameCannotBeEmpty() {
//        new MyField("");
//    }
//
//    @Test
//    public void testNotIndexed() {
//        assertEquals(Field.Search.NO, f.notIndexed().getSearch());
//    }
//
//    @Test
//    public void testIndexed() {
//        assertEquals(Field.Search.MATCH, f.indexed().getSearch());
//    }
//
//    @Test
//    public void testTokenized() {
//        assertEquals(Field.Search.FULL, f.tokenized().getSearch());
//    }
//
//    @Test
//    public void testNotStored() {
//        assertEquals(Field.Store.NO, f.notStored().getStore());
//    }
//
//    @Test
//    public void testStored() {
//        assertEquals(Field.Store.YES, f.stored().getStore());
//    }
//
//    @Test
//    public void testNotRequired() {
//        assertFalse(f.notRequired().isRequired());
//    }
//
//    @Test
//    public void testRequired() {
//        assertTrue(f.required().isRequired());
//    }
//
//    @Test
//    public void testNullable() {
//        assertTrue(f.nullable().isNullable());
//    }
//
//    @Test
//    public void testNotNullable() {
//        assertFalse(f.notNullable().isNullable());
//    }
//
//}
