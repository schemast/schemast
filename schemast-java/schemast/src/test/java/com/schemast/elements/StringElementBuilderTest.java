package com.schemast.elements;

import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringElementBuilderTest extends ElementTestBase {
    private StringElement.Builder b;

	@Override
	protected void setType() {
		b = new Element.Builder().ofString(NAME);
		super.b = b;
	}

	@Test
	public void testNullable() {
		StringElement be = b.nullable(true).build();
		assertTrue(be.isNullable());
	}

	@Test
	public void testNotNullable() {
		StringElement be = b.nullable(false).build();
		assertFalse(be.isNullable());
	}

	@Test
	public void testIsNotNullableByDefault() {
		StringElement be = b.build();
		assertFalse(be.isNullable());
	}

	@Test
	public void testMinLength() {
		final Long min = 5L;
		StringElement be = b.minLength(min).build();
		assertTrue(be.getMinLength().isPresent());
		assertEquals(min, be.getMinLength().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testNegativeMinLength() {
		b.minLength(-5L).build();
	}

	@Test
	public void testZeroMinLength() {
		final Long min = 0L;
		StringElement be = b.minLength(min).build();
		assertTrue(be.getMinLength().isPresent());
		assertEquals(min, be.getMinLength().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testNullMinLength() {
		b.minLength(null).build();
	}

	@Test
	public void testMaxLength() {
		final Long max = 12L;
		StringElement be = b.maxLength(max).build();
		assertTrue(be.getMaxLength().isPresent());
		assertEquals(max, be.getMaxLength().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testNegativeMaxLength() {
		b.maxLength(-1L).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testZeroMaxLength() {
		b.maxLength(0L).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testNullMaxLength() {
		b.maxLength(null).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testMinLengthGreaterThanMaxLength() {
		b.minLength(5L).maxLength(2L).build();
	}

	@Test
	public void testMinLengthSameAsMinLength() {
		StringElement be = b.maxLength(5L).minLength(5L).build();
		assertEquals(new Long(5L), be.getMinLength().get());
		assertEquals(new Long(5L), be.getMaxLength().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testMaxLengthLessThanMinLength() {
		b.maxLength(2L).minLength(5L).build();
	}

	@Test
	public void testDefault() {
		final String def = "a default";
		StringElement be = b.withDefault(def).build();
		assertTrue(be.getDefault().wasSet());
		assertEquals(def, be.getDefault().getOpt().get());
	}

	@Test
	public void testDefaultNotSet() {
		StringElement be = b.build();
		assertFalse(be.getDefault().wasSet());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetValueWhenDefaultNotSet() {
		StringElement be = b.build();
		be.getDefault().getOpt().get();
	}

	@Test
	public void testNullDefaultWhenNullable() {
		StringElement be = b.withDefault(null).nullable(true).build();
		assertTrue(be.getDefault().wasSet());
		assertFalse(be.getDefault().getOpt().isPresent());
	}

	@Test(expected = InvalidElementException.class)
	public void testNullDefaultWhenNotNullable() {
		b.withDefault(null).nullable(false).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testDefaultLessThanMinLength() {
		final String def = "bad";
		final Long min = 4L;
		b.withDefault(def).minLength(min).build();
	}

	@Test
	public void testDefaultEqualToMinLength() {
		final String def = "good";
		final Long min = 3L;
		StringElement be = b.withDefault(def).minLength(min).build();
		assertEquals(def, be.getDefault().getOpt().get());
		assertEquals(min, be.getMinLength().get());
	}

	@Test
	public void testDefaultGreaterThanMinLengthAndNoMaxLength() {
		final String def = "good";
		final Long min = 3L;
		StringElement be = b.withDefault(def).minLength(min).build();
		assertEquals(def, be.getDefault().getOpt().get());
		assertEquals(min, be.getMinLength().get());
	}

	@Test
	public void testDefaultEqualToMaxLength() {
		final String def = "good";
		final Long max = 4L;
		StringElement be = b.withDefault(def).maxLength(max).build();
		assertEquals(def, be.getDefault().getOpt().get());
		assertEquals(max, be.getMaxLength().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testDefaultGreaterThanMaxLength() {
		final String def = "bad";
		final Long max = 2L;
		StringElement be = b.withDefault(def).maxLength(max).build();
		assertEquals(def, be.getDefault().getOpt().get());
		assertEquals(max, be.getMaxLength().get());
	}

	@Test
	public void testDefaultLessThanMaxLengthAndNoMinLength() {
		final String def = "good";
		final Long max = 100L;
		StringElement be = b.withDefault(def).maxLength(max).build();
		assertEquals(def, be.getDefault().getOpt().get());
		assertEquals(max, be.getMaxLength().get());
	}

	@Test
	public void testDefaultBetweenMinAndMaxLength() {
		final String def = "good";
		final Long min = 3L;
		final Long max = 5L;
		StringElement be = b.withDefault(def).minLength(min).maxLength(max).build();
		assertEquals(def, be.getDefault().getOpt().get());
		assertEquals(min, be.getMinLength().get());
		assertEquals(max, be.getMaxLength().get());
	}
}
