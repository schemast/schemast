package com.schemast.schemas;

import test.TestData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeaderTest extends TestData {

    @Test
    public void testValidHeader() {
        Header h = new Header(NAMESPACE, NAME, VERSION);
        assertEquals(NAMESPACE, h.getNamespace());
	    assertEquals(NAME, h.getName());
	    assertEquals(VERSION, h.getVersion());
    }

    @Test(expected = InvalidHeaderException.class)
    public void testNameCannotBeNull() {
	    new Header(NAMESPACE, null, VERSION);
    }

    @Test(expected = InvalidHeaderException.class)
    public void testNameCannotBeEmpty() {
	    new Header(NAMESPACE, "", VERSION);
    }

    @Test(expected = InvalidHeaderException.class)
    public void testNamespaceCannotBeNull() {
	    new Header(null, NAME, VERSION);
    }

    @Test(expected = InvalidHeaderException.class)
    public void testNamespaceCannotBeEmpty() {
	    new Header("", NAME, VERSION);
    }

    @Test(expected = InvalidHeaderException.class)
    public void testVersionCannotBeNegative() {
	    new Header(NAMESPACE, NAME, -1);
    }

    @Test(expected = InvalidHeaderException.class)
    public void testVersionCannotBeZero() {
	    new Header(NAMESPACE, NAME, 0);
    }
}
