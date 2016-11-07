package com.schemast.elements;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BooleanElementBuilderTest extends ElementTestBase {
    private BooleanElement.Builder b;

	@Override
	protected void setType() {
		b = new Element.Builder().ofBoolean(NAME);
		super.b = b;
	}

	@Test
	public void testDefaultWithTrue() {
		BooleanElement be = b.withDefault(true).build();
		assertTrue(be.getDefault().isPresent());
		assertTrue(be.getDefault().get());
	}

	@Test
	public void testDefaultWithFalse() {
		BooleanElement be = b.withDefault(false).build();
		assertTrue(be.getDefault().isPresent());
		assertFalse(be.getDefault().get());
	}

	@Test
	public void testDefaultNotSet() {
		BooleanElement be = b.build();
		assertFalse(be.getDefault().isPresent());
	}
}
