package com.schemast.elements;

import org.junit.Test;
import test.TestData;

import static org.junit.Assert.assertTrue;

public class ElementBuilderTest extends TestData {

    @Test
    public void testBuilderReturnsCorrectType() {
        Element e;

        e = new Element.Builder().ofMap(LABEL).add(mockElement()).build();
        assertTrue(e.getClass() == MapElement.class);

        e = new Element.Builder().ofString(LABEL).build();
        assertTrue(e.getClass() == StringElement.class);

        e = new Element.Builder().ofInt(LABEL).build();
        assertTrue(e.getClass() == IntElement.class);
    }

    @Test(expected = InvalidElementException.class)
    public void testBuilderWithNullLabel() {
        new Element.Builder().ofString(null).build();
    }

    @Test(expected = InvalidElementException.class)
    public void testBuilderWithEmptyLabel() {
        new Element.Builder().ofMap("").build();
    }
}
