package com.schemast.schemas;

import org.junit.Before;
import org.junit.Test;
import test.MockElement;
import test.TestData;

import static org.junit.Assert.*;

public class StructureTest extends TestData {
    private Structure s;

    @Before
    public void setUp() {
        s = new Structure();
    }

    @Test
    public void testValidStructure() {
        s.append(new MockElement(LABEL));
    }

    @Test
    public void testAppendAndFind() {
        assertNull(s.find(LABEL));
        assertNull(s.find(LABEL2));

        s.append(new MockElement(LABEL));

        assertEquals(LABEL, s.find(LABEL).getLabel());
        assertNull(s.find(LABEL2));

        s.append(new MockElement(LABEL2));

        assertEquals(LABEL, s.find(LABEL).getLabel());
        assertEquals(LABEL2, s.find(LABEL2).getLabel());
    }

    @Test(expected = InvalidStructureException.class)
    public void testAppendNull() {
        s.append(null);
    }

    @Test(expected = InvalidStructureException.class)
    public void testAppendDuplicate() {
        s.append(new MockElement(LABEL));
        s.append(new MockElement(LABEL));
    }

    @Test
    public void testEmpty() {
        assertTrue(s.isEmpty());
        s.append(new MockElement(LABEL));
        assertFalse(s.isEmpty());
    }

    @Test
    public void testGetAll() {
        assertEquals(0, s.getAll().size());
        s.append(new MockElement(LABEL));
        assertEquals(1, s.getAll().size());
    }

}
