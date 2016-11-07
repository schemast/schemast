package com.schemast.schemas;

import com.schemast.models.InvalidModelException;
import org.junit.Test;
import test.TestData;

import static com.schemast.Constants.MIN_VERSION;
import static org.junit.Assert.assertEquals;

public class MetadataTest extends TestData {

    @Test
    public void testValidMetadata() {
	    Metadata m = new Metadata.Builder().name(NAME).version(VERSION).build();
	    assertEquals(NAME, m.getName());
	    assertEquals(VERSION, m.getVersion());
    }

    @Test(expected = InvalidModelException.class)
    public void testNameCannotBeNull() {
	    new Metadata.Builder().name(null).version(VERSION).build();
    }

	@Test(expected = InvalidModelException.class)
	public void testNameCannotBeEmpty() {
		new Metadata.Builder().name("").version(VERSION).build();
	}

	@Test(expected = InvalidModelException.class)
	public void testNameIsRequired() {
		new Metadata.Builder().version(VERSION).build();
	}

	@Test(expected = InvalidSchemaException.class)
	public void testVersionMustBeValid() {
		new Metadata.Builder().name(NAME).version(MIN_VERSION - 1).build();
	}

	@Test(expected = InvalidSchemaException.class)
	public void testVersionIsRequired() {
		new Metadata.Builder().name(NAME).build();
	}
}
