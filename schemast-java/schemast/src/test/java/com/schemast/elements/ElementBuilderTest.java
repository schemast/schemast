package com.schemast.elements;

import com.schemast.models.InvalidModelException;
import org.junit.Test;
import test.TestData;

import static org.junit.Assert.assertTrue;

public class ElementBuilderTest extends TestData {

    @Test
    public void testBuilderReturnsCorrectType() {
        Element e;

        e = new Element.Builder().ofMap(NAME).add(mockElement()).build();
        assertTrue(e.getClass() == MapElement.class);

        e = new Element.Builder().ofString(NAME).build();
        assertTrue(e.getClass() == StringElement.class);

        e = new Element.Builder().ofInt(NAME).build();
        assertTrue(e.getClass() == IntElement.class);
    }

    @Test(expected = InvalidModelException.class)
    public void testBuilderWithNullName() {
        new Element.Builder().ofString(null).build();
    }

    @Test(expected = InvalidElementException.class)
    public void testBuilderWithEmptyName() {
        new Element.Builder().ofMap("").build();
    }
}
