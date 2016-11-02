package com.schemast.schemas.elements;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ElementTest {
    private static final String LABEL = "myElement";

    @Test
    public void testBuilderReturnsCorrectType() {
        Element e;

        e = new Element.Builder().label(LABEL).type(Element.Type.ARRAY).build();
        assertTrue(e.getClass() == ArrayElement.class);

        e = new Element.Builder().label(LABEL).type(Element.Type.ENUM).build();
        assertTrue(e.getClass() == EnumElement.class);

        e = new Element.Builder().label(LABEL).type(Element.Type.FIELD).build();
        assertTrue(e.getClass() == FieldElement.class);

        e = new Element.Builder().label(LABEL).type(Element.Type.ID).build();
        assertTrue(e.getClass() == IdElement.class);

        e = new Element.Builder().label(LABEL).type(Element.Type.MAP).build();
        assertTrue(e.getClass() == MapElement.class);

        e = new Element.Builder().label(LABEL).type(Element.Type.SUBTYPE).build();
        assertTrue(e.getClass() == SubtypeElement.class);
    }

    @Test(expected = InvalidElementException.class)
    public void testBuilderWithNullType() {
        new Element.Builder().label(LABEL).type(null);
    }

    @Test(expected = InvalidElementException.class)
    public void testBuilderWithNullLabel() {
        new Element.Builder().label(null).type(Element.Type.ARRAY).build();
    }

    @Test(expected = InvalidElementException.class)
    public void testBuilderWithEmptyLabel() {
        new Element.Builder().label("").type(Element.Type.ARRAY).build();
    }

}
