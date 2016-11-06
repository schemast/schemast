package com.schemast.elements;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntElementBuilderTest extends ElementTestBase {
    private IntElement.Builder b;

	@Override
	protected void setType() {
		b = new Element.Builder().ofInt(LABEL);
		super.b = b;
	}

	@Test
	public void testMinValue() {
		final Integer min = -123;
		IntElement be = b.minValue(min).build();
		assertTrue(be.getMinValue().isPresent());
		assertEquals(min, be.getMinValue().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testNullMinValue() {
		b.minValue(null).build();
	}

	@Test
	public void testMaxValue() {
		final Integer max = -123;
		IntElement be = b.maxValue(max).build();
		assertTrue(be.getMaxValue().isPresent());
		assertEquals(max, be.getMaxValue().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testNullMaxValue() {
		b.maxValue(null).build();
	}

	@Test
	public void testMinValueSameAsMaxValue() {
		IntElement be = b.minValue(5).maxValue(5).build();
		assertEquals(new Integer(5), be.getMinValue().get());
		assertEquals(new Integer(5), be.getMaxValue().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testMinValueGreaterThanMaxValue() {
		b.minValue(5).maxValue(2).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testMaxValueLessThanMaxValue() {
		b.maxValue(2).minValue(5).build();
	}

	@Test
	public void testDefault() {
		final Integer def = -123;
		IntElement be = b.withDefault(def).build();
		assertTrue(be.getDefault().isPresent());
		assertEquals(def, be.getDefault().get());
	}

	@Test
	public void testDefaultNotSet() {
		IntElement be = b.build();
		assertFalse(be.getDefault().isPresent());
	}

	@Test(expected = InvalidElementException.class)
	public void testNullDefault() {
		b.withDefault(null).build();
	}

	@Test(expected = InvalidElementException.class)
	public void testDefaultLessThanMinValue() {
		final Integer def = 4;
		final Integer min = 12;
		b.withDefault(def).minValue(min).build();
	}

	@Test
	public void testDefaultGreaterThanMinValueAndNoMaxValue() {
		final Integer def = 123;
		final Integer min = 12;
		IntElement be = b.withDefault(def).minValue(min).build();
		assertEquals(def, be.getDefault().get());
		assertEquals(min, be.getMinValue().get());
	}

	@Test(expected = InvalidElementException.class)
	public void testDefaultGreaterThanMaxValue() {
		final Integer def = 4;
		final Integer max = 2;
		b.withDefault(def).maxValue(max).build();
	}

	@Test
	public void testDefaultLessThanMaxValueAndNoMinValue() {
		final Integer def = 12;
		final Integer max = 100;
		IntElement be = b.withDefault(def).maxValue(max).build();
		assertEquals(def, be.getDefault().get());
		assertEquals(max, be.getMaxValue().get());
	}

	@Test
	public void testDefaultBetweenMinAndMaxValue() {
		final Integer def = 123;
		final Integer min = 12;
		final Integer max = 200;
		IntElement be = b.withDefault(def).minValue(min).maxValue(max).build();
		assertEquals(def, be.getDefault().get());
		assertEquals(min, be.getMinValue().get());
		assertEquals(max, be.getMaxValue().get());
	}
}
