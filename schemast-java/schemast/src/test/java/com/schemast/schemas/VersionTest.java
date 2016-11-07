package com.schemast.schemas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VersionTest {
	private static final int VERSION = 3;

	@Test
	public void testVersion() {
		Version v = new Version(VERSION);
		assertEquals(VERSION, v.get());
	}

	@Test(expected = InvalidSchemaException.class)
	public void testVersionCannotBeNegative() {
		new Version(-1);
	}

	@Test(expected = InvalidSchemaException.class)
	public void testVersionCannotBeZero() {
		new Version(0);
	}
}
