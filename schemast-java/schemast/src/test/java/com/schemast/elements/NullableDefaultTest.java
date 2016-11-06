package com.schemast.elements;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NullableDefaultTest {

	@Test
	public void testDefaultWasSet() {
		NullableDefault<Integer> def = new NullableDefault<>();
		def.set(4);
		assertTrue(def.wasSet());
	}

	@Test
	public void testDefaultWasNotSet() {
		NullableDefault<Double> def = new NullableDefault<>();
		assertFalse(def.wasSet());
	}

	@Test
	public void testGetOptWhenSet() {
		final Long val = 123L;
		NullableDefault<Long> def = new NullableDefault<>();
		def.set(val);
		assertTrue(def.wasSet());
		assertEquals(val, def.getOpt().get());
	}

	@Test
	public void testGetOptWhenSetToNull() {
		NullableDefault<String> def = new NullableDefault<>();
		def.set(null);
		assertTrue(def.wasSet());
		assertFalse(def.getOpt().isPresent());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetOptWhenNotSet() {
		NullableDefault<Long> def = new NullableDefault<>();
		def.getOpt();
	}
}
