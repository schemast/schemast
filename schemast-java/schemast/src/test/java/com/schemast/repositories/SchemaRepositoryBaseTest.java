package com.schemast.repositories;

import com.schemast.schemas.Schema;
import org.junit.Before;
import org.junit.Test;
import test.TestData;

import static org.junit.Assert.*;

public abstract class SchemaRepositoryBaseTest extends TestData {
    private final String BAD_NAMESPACE = "badNS";
    private final String BAD_NAME = "badName";
    private final int BAD_VERSION = 666;

    private Schema first = testSchema();
    private Schema second = testSchema(NAMESPACE2, NAME2, VERSION);
    private Schema secondV2 = testSchema(NAMESPACE2, NAME2, VERSION2);
    private SchemaRepository repo;

    protected abstract SchemaRepository newInstance();

    @Before
    public void doBefore() {
        repo = newInstance();
    }

    @Test
    public void testPutAndGet() {
        assertNull(repo.get(NAMESPACE, NAME));

        repo.put(first);
        assertNotNull(repo.get(NAMESPACE, NAME));
    }

    @Test
    public void testPutAndGetSpecificVersion() {
        assertNull(repo.get(NAMESPACE, NAME));
        assertNull(repo.get(NAMESPACE, NAME, VERSION));

        repo.put(first);
        assertNotNull(repo.get(NAMESPACE, NAME));
        assertNotNull(repo.get(NAMESPACE, NAME, VERSION));
    }

    @Test
    public void testPutSameSchemaVersionTwice() {
        repo.put(first);
        repo.put(first);

        assertNotNull(repo.get(NAMESPACE, NAME));
        assertNotNull(repo.get(NAMESPACE, NAME, VERSION));
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testPutWithNull() {
        repo.put(null);
    }

    @Test
    public void testPutTwoVersionsCanRetrieveEach() {
        repo.put(second);
        repo.put(secondV2);

        assertEquals(VERSION, repo.get(NAMESPACE2, NAME2, VERSION).getVersion());
        assertEquals(VERSION2, repo.get(NAMESPACE2, NAME2, VERSION2).getVersion());
    }

    @Test
    public void testPutTwoVersionsOfSameSchemaGetRetrievesLatest() {
        repo.put(second);
        repo.put(secondV2);
        assertEquals(VERSION2, repo.get(NAMESPACE2, NAME2).getVersion());
    }

    @Test
    public void testPutDifferentSchemasCanRetrieveEach() {
        repo.put(first);
        repo.put(second);
        repo.put(secondV2);

        assertEquals(VERSION, repo.get(NAMESPACE, NAME).getVersion());
        assertEquals(VERSION, repo.get(NAMESPACE, NAME, VERSION).getVersion());
        assertEquals(VERSION2, repo.get(NAMESPACE2, NAME2).getVersion());
        assertEquals(VERSION, repo.get(NAMESPACE2, NAME2, VERSION).getVersion());
        assertEquals(VERSION2, repo.get(NAMESPACE2, NAME2, VERSION2).getVersion());
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testGetWithNullNamespace() {
        repo.put(first);
        repo.get(NAMESPACE, null);
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testGetWithEmptyNamespace() {
        repo.put(first);
        repo.get(NAMESPACE, "");
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testGetWithNullName() {
        repo.put(first);
        repo.get(NAMESPACE, null);
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testGetWithEmptyName() {
        repo.put(first);
        repo.get(NAMESPACE, "");
    }

    @Test
    public void testGetWithBadName() {
        repo.put(first);
        assertNull(repo.get(NAMESPACE, BAD_NAME));
    }

    @Test
    public void testGetWithBadNamespace() {
        repo.put(first);
        assertNull(repo.get(BAD_NAMESPACE, NAME));
    }

    @Test
    public void testGetWithBadVersion() {
        repo.put(first);
        assertNull(repo.get(NAMESPACE, NAME, BAD_VERSION));
    }

    @Test
    public void testDelete() {
        repo.put(first);
        assertTrue(repo.delete(NAMESPACE, NAME));
        assertNull(repo.get(NAMESPACE, NAME));
    }

    @Test
    public void testDeleteWhenEmpty() {
        assertFalse(repo.delete(NAMESPACE, NAME));
    }

    @Test
    public void testDeleteWithDifferentSchemas() {
        repo.put(first);
        repo.put(second);

        assertTrue(repo.delete(NAMESPACE, NAME));
        assertNull(repo.get(NAMESPACE, NAME));
        assertNotNull(repo.get(NAMESPACE2, NAME2));
    }

    @Test
    public void testDeleteSpecificVersion() {
        repo.put(second);
        repo.put(secondV2);

        assertTrue(repo.delete(NAMESPACE2, NAME2, VERSION2));
        assertNull(repo.get(NAMESPACE2, NAME2, VERSION2));
        assertEquals(VERSION, repo.get(NAMESPACE2, NAME2).getVersion());
    }

    @Test
    public void testDeleteAllVersions() {
        repo.put(second);
        repo.put(secondV2);

        assertTrue(repo.delete(NAMESPACE2, NAME2));
        assertNull(repo.get(NAMESPACE2, NAME2, VERSION));
        assertNull(repo.get(NAMESPACE2, NAME2, VERSION2));
    }

    @Test
    public void testDeleteNamespace() {
        repo.put(first);
        repo.put(second);
        repo.put(secondV2);

        assertTrue(repo.delete(NAMESPACE2));
        assertNull(repo.get(NAMESPACE2, NAME2));
        assertNotNull(repo.get(NAMESPACE, NAME));
    }

    @Test
    public void testDeleteWithBadNamespace() {
        repo.put(first);
        assertFalse(repo.delete(BAD_NAMESPACE));
        assertFalse(repo.delete(BAD_NAMESPACE, BAD_NAME));
    }

    @Test
    public void testDeleteWithBadName() {
        repo.put(first);
        assertFalse(repo.delete(NAMESPACE, BAD_NAME));
    }

    @Test
    public void testDeleteWithBadVersion() {
        repo.put(first);
        assertFalse(repo.delete(NAMESPACE, NAME, BAD_VERSION));
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testDeleteWithNullNamespace() {
        repo.put(first);
        assertFalse(repo.delete(null, NAME));
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testDeleteByNamespaceWithNullNamespace() {
        repo.put(first);
        assertFalse(repo.delete(null));
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testDeleteByNamespaceWithEmptyNamespace() {
        repo.put(first);
        repo.delete("");
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testDeleteWithEmptyNamespace() {
        repo.put(first);
        repo.delete("", NAME);
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testDeleteWithNullName() {
        repo.put(first);
        repo.delete(NAMESPACE, null);
    }

    @Test(expected = SchemaRepositoryException.class)
    public void testDeleteWithEmptyName() {
        repo.put(first);
        repo.delete(NAMESPACE, "");
    }

}
