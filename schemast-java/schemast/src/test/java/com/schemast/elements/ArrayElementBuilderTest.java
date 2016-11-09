package com.schemast.elements;

import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayElementBuilderTest extends ElementTestBase {
    private ArrayElement.Builder b;

	@Override
	protected void setType() {
		b = new Element.Builder().ofArray(NAME);
		super.b = b;
	}

	@Test
	public void testMaxSize() {
		final Integer max = 12;
		ArrayElement be = b.maxSize(max).build();
		assertTrue(be.getMaxSize().isPresent());
		assertEquals(max, be.getMaxSize().get());
	}

	@Test
	public void testMaxSizeNotSet() {
		ArrayElement be = b.build();
		assertFalse(be.getMaxSize().isPresent());
	}

	@Test(expected = InvalidElementException.class)
	public void testNegativeMaxSize() {
		b.maxSize(-1).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testZeroMaxSize() {
		b.maxSize(0).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testOneMaxSize() {
		b.maxSize(1).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testNullMaxSize() {
		b.maxSize(null).build();
	}
}
