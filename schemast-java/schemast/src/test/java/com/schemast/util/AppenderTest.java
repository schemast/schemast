package com.schemast.util;

import org.junit.Before;
import org.junit.Test;
import test.TestData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppenderTest extends TestData {

	private class TestNamed extends Named {
		TestNamed(String name) {
			super(name);
		}
	}

	private Appender<TestNamed> app;

	@Before
	public void setUp() {
		app = new Appender<>();
	}

	@Test
	public void testAppend() {
		app.append(new TestNamed(NAME));
		app.append(new TestNamed(NAME2));
		assertFalse(app.isEmpty());
		assertEquals(2, app.getItems().size());
	}

	@Test(expected = NullPointerException.class)
	public void testAppendNull() {
		app.append(null);
	}

	@Test(expected = IllegalStateException.class)
	public void testAppendDuplicate() {
		app.append(new TestNamed(NAME));
		app.append(new TestNamed(NAME));
	}

	@Test
	public void testEmpty() {
		assertTrue(app.isEmpty());
	}
}
