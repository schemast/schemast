package com.schemast.util;

import com.schemast.models.InvalidModelException;
import org.junit.Test;

import static com.schemast.Constants.NAME;
import static org.junit.Assert.assertEquals;

public class NameTest {

	@Test
	public void testName() {
		Name n = new Name(NAME);
		assertEquals(NAME, n.get());
	}

	@Test(expected = InvalidModelException.class)
	public void testNullName() {
		new Name(null);
	}
}
