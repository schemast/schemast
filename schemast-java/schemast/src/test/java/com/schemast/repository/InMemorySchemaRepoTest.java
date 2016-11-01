package com.schemast.repository;

import com.schemast.schemas.Schema;
import com.schemast.schemas.fields.StringField;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemorySchemaRepoTest {
    private String SCHEMA = "1st";
    private String SECOND_SCHEMA = "2nd";
    private String BAD_SCHEMA = "bad";

    private int VERSION = 4;
    private int FIRST_VERSION = 1;
    private int SECOND_VERSION = 2;
    private int BAD_VERSION = 666;

    private Schema first = new Schema.Builder().name(SCHEMA).version(VERSION).field(new StringField("blah")).build();
    private Schema secondV1 = new Schema.Builder().name(SECOND_SCHEMA).version(FIRST_VERSION).field(new StringField("blah")).build();
    private Schema secondV2 = new Schema.Builder().name(SECOND_SCHEMA).version(SECOND_VERSION).field(new StringField("blah")).build();

    private SchemaRepo repo;

    @Before
    public void doBefore() {
        repo = new InMemorySchemaRepo();
    }

    @Test
    public void testPutAndGet() {
        repo.put(first);
        assertNotNull(repo.get(SCHEMA));
    }

    @Test
    public void testPutAndGetSpecificVersion() {
        repo.put(first);
        assertNotNull(repo.get(SCHEMA, VERSION));
    }

    @Test
    public void testPutSameSchemaTwice() {
        repo.put(first);
        repo.put(first);
        assertNotNull(repo.get(SCHEMA));
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNull() {
        repo.put(null);
    }

    @Test
    public void testPutTwoVersionsGetRetrievesLatest() {
        repo.put(secondV1);
        repo.put(secondV2);
        assertEquals(SECOND_VERSION, repo.get(SECOND_SCHEMA).getVersion());
    }

    @Test
    public void testPutTwoVersionsCanRetrieveEach() {
        repo.put(secondV1);
        repo.put(secondV2);
        assertEquals(FIRST_VERSION, repo.get(SECOND_SCHEMA, FIRST_VERSION).getVersion());
        assertEquals(SECOND_VERSION, repo.get(SECOND_SCHEMA, SECOND_VERSION).getVersion());
    }

    @Test
    public void testPutDifferentSchemasCanRetrieveEach() {
        repo.put(first);
        repo.put(secondV1);
        repo.put(secondV2);
        assertEquals(VERSION, repo.get(SCHEMA).getVersion());
        assertEquals(VERSION, repo.get(SCHEMA, VERSION).getVersion());
        assertEquals(FIRST_VERSION, repo.get(SECOND_SCHEMA, FIRST_VERSION).getVersion());
        assertEquals(SECOND_VERSION, repo.get(SECOND_SCHEMA).getVersion());
        assertEquals(SECOND_VERSION, repo.get(SECOND_SCHEMA, SECOND_VERSION).getVersion());
    }

    @Test
    public void testGetWithBadSchemaName() {
        assertNull(repo.get(BAD_SCHEMA));
    }

    @Test
    public void testGetWithBadVersion() {
        repo.put(first);
        assertNotNull(repo.get(SCHEMA, VERSION));
        assertNull(repo.get(SCHEMA, BAD_VERSION));
    }

    @Test
    public void testPutAndGetDifferentVersions() {
        repo.put(secondV1);
        repo.put(secondV2);
        assertEquals(SECOND_VERSION, repo.get(SECOND_SCHEMA).getVersion());
        assertNull(repo.get(SECOND_SCHEMA, BAD_VERSION));
        assertNotNull(repo.get(SECOND_SCHEMA, FIRST_VERSION));
        assertNotNull(repo.get(SECOND_SCHEMA, SECOND_VERSION));
    }

    @Test
    public void testGetWithNull() {
        assertNull(repo.get(null));
    }

    @Test
    public void testGetWithEmptyName() {
        assertNull(repo.get(""));
    }

    @Test
    public void testDelete() {
        repo.put(first);
        assertTrue(repo.delete(SCHEMA));
        assertNull(repo.get(SCHEMA));
    }

    @Test
    public void testDeleteWithMultipleDifferentSchemas() {
        repo.put(first);
        repo.put(secondV1);
        assertTrue(repo.delete(SCHEMA));
        assertNull(repo.get(SCHEMA));
        assertNotNull(repo.get(SECOND_SCHEMA));
    }

    @Test
    public void testDeleteWithMultipleSchemaVersions() {
        repo.put(secondV1);
        repo.put(secondV2);
        assertTrue(repo.delete(SECOND_SCHEMA, SECOND_VERSION));
        assertNull(repo.get(SECOND_SCHEMA, SECOND_VERSION));
        assertNotNull(repo.get(SECOND_SCHEMA, FIRST_VERSION));
        assertNotNull(repo.get(SECOND_SCHEMA));
    }

    @Test
    public void testDeleteWhenSchemaDoesNotExist() {
        assertFalse(repo.delete(BAD_SCHEMA));
    }

    @Test
    public void testDeleteWithNull() {
        assertFalse(repo.delete(null));
    }

    @Test
    public void testDeleteWithEmptyName() {
        assertFalse(repo.delete(""));
    }

}
