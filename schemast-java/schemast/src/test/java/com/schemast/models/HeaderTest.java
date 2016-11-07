package com.schemast.models;

import test.TestData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeaderTest extends TestData {

    @Test
    public void testValidHeader() {
        Header h = new Header(NAMESPACE);
        assertEquals(NAMESPACE, h.getNamespace());
    }

    @Test(expected = InvalidModelException.class)
    public void testNamespaceCannotBeNull() {
	    new Header(null);
    }

	@Test(expected = InvalidModelException.class)
    public void testNamespaceCannotBeEmpty() {
	    new Header("");
    }
}
