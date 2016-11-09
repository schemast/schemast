package com.schemast.elements;

import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnumElementBuilderTest extends ElementTestBase {
	private static final String VALUE = "val";
	private static final String VALUE2 = "val 2";
    private EnumElement.Builder b;

	private EnumElement.Builder builderNoValues() {
		return new Element.Builder().ofEnum(NAME);
	}

	@Override
	protected void setType() {
		b = new Element.Builder().ofEnum(NAME).add(VALUE).add(VALUE2);
		super.b = b;
	}

	@Test
	public void testNullable() {
		EnumElement be = b.nullable(true).build();
		assertTrue(be.isNullable());
	}

	@Test
	public void testNotNullable() {
		EnumElement be = b.nullable(false).build();
		assertFalse(be.isNullable());
	}

	@Test
	public void testIsNotNullableByDefault() {
		EnumElement be = b.build();
		assertFalse(be.isNullable());
	}

	@Test
	public void testDefault() {
		final String def = VALUE;
		EnumElement be = b.withDefault(def).build();
		assertTrue(be.getDefault().wasSet());
		assertEquals(def, be.getDefault().getOpt().get());
	}

	@Test
	public void testDefaultNotSet() {
		EnumElement be = b.build();
		assertFalse(be.getDefault().wasSet());
	}

	@Test(expected = InvalidElementException.class)
	public void testDefaultWhenDefaultNotAChoice() {
		final String def = "this is not a valid choice";
		b.withDefault(def).build();
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetValueWhenDefaultNotSet() {
		EnumElement be = b.build();
		be.getDefault().getOpt().get();
	}

	@Test
	public void testNullDefaultWhenNullable() {
		EnumElement be = b.withDefault(null).nullable(true).build();
		assertTrue(be.getDefault().wasSet());
		assertFalse(be.getDefault().getOpt().isPresent());
	}

	@Test(expected = InvalidElementException.class)
	public void testNullDefaultWhenNotNullable() {
		b.withDefault(null).nullable(false).build();
	}

	@Test
	public void testAdd() {
		EnumElement be = builderNoValues().add(VALUE).add(VALUE2).build();
		assertEquals(2, be.getValues().size());
		assertTrue(be.getValues().contains(VALUE));
		assertTrue(be.getValues().contains(VALUE2));
	}

	@Test(expected = InvalidElementException.class)
	public void testAddNull() {
		builderNoValues().add(null);
	}

	@Test(expected = InvalidElementException.class)
	public void testFailsWithZeroValues() {
		builderNoValues().build();
	}

	@Test(expected = InvalidElementException.class)
	public void testFailsWithOneValue() {
		builderNoValues().add(VALUE).build();
	}
}
