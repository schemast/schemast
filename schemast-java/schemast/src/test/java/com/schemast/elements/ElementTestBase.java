package com.schemast.elements;

import org.junit.Before;
import org.junit.Test;
import test.TestData;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class ElementTestBase extends TestData {
	protected ElementBuilder b;

	protected abstract void setType();

    @Before
    public void setUp() {
        setType();
    }

    @Test
    public void testIsOptional() {
        assertTrue(b.optional(true).build().isOptional());
    }

	@Test
	public void testIsNotOptional() {
		assertFalse(b.optional(false).build().isOptional());
	}

	@Test
	public void testIsNotOptionalByDefault() {
		assertFalse(b.build().isOptional());
	}

}
