package com.schemast.schemas;

import com.schemast.models.InvalidModelException;
import org.junit.Test;
import test.TestData;

import static org.junit.Assert.*;

public class SchemaTest extends TestData {

    @Test
    public void testValidSchema() {
	    Schema s = testSchema();
        assertEquals(NAMESPACE, s.getNamespace());
	    assertEquals(NAME, s.getName());
	    assertEquals(VERSION, s.getMetadata().getVersion());
	    assertEquals(1, s.getElements().size());
    }

    @Test(expected = InvalidModelException.class)
    public void testNullNamespace() {
	    new Schema.Builder(null).metadata(testMetadata()).append(mockElement()).build();
    }

	@Test(expected = InvalidModelException.class)
	public void testEmptyNamespace() {
		new Schema.Builder("").metadata(testMetadata()).append(mockElement()).build();
	}

	@Test(expected = InvalidSchemaException.class)
	public void testMetadataCannotBeNull() {
		new Schema.Builder(NAMESPACE).metadata(null).append(mockElement()).build();
	}

    @Test
    public void testAppend() {
	    Schema s = new Schema.Builder(NAMESPACE)
			    .metadata(testMetadata())
			    .append(mockElement())
			    .append(mockElement(NAME2))
			    .build();
	    assertEquals(2, s.getElements().size());
    }

    @Test(expected = NullPointerException.class)
    public void testAppendNull() {
	    new Schema.Builder(NAMESPACE).metadata(testMetadata()).append(null).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendDuplicate() {
	    new Schema.Builder(NAMESPACE)
			    .metadata(testMetadata())
			    .append(mockElement(NAME))
			    .append(mockElement(NAME))
			    .build();
    }
}
