package com.schemast.elements;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapElementBuilderTest extends ElementTestBase {
    private MapElement.Builder b;

    @Override
    protected void setType() {
        b = new Element.Builder().ofMap(NAME).add(mockElement());
        super.b = b;
    }

    @Test(expected = InvalidElementException.class)
    public void testEmptyMap() {
	    new Element.Builder().ofMap(NAME).build();
    }

	@Test
	public void testAddElements() {
		MapElement m = b.add(mockElement(NAME2)).build();
		assertEquals(2, m.getElements().size());
	}

    @Test(expected = InvalidElementException.class)
    public void testAddWithNull() {
        b.add(null);
    }

    @Test(expected = InvalidElementException.class)
    public void testAddDuplicate() {
        b.add(mockElement());
    }

}
