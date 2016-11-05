package com.schemast.parsers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SchemaParserRegistryTest {
    private final String BASE = "test.plugins.";

    // safe packages
    private final String BAD_PACKAGE = "this.is.garbage";
    private final String MOCK_PACKAGE = BASE + "mock";
    private final String NULLING_PACKAGE = BASE + "nulling";

    // unsafe packages
    private final String DUPLICATE_PACKAGE = BASE + "duplicate";
    private final String INVALID_PACKAGE = BASE + "invalid";
    private final String NOT_A_PARSER_PACKAGE = BASE + "not_a_parser";
    private final String NULLING_DIFFERENT_PACKAGE = DUPLICATE_PACKAGE + ".nulling";

    // types
    private final String TYPE_BAD = "badType";
    private final String TYPE_MOCK = "mock";
    private final String TYPE_NULLING = "nulling";

    // auto-scanned types
    private final String TEST_1 = "TestSchemaParser1";
    private final String TEST_2 = "TestSchemaParser2";

    private SchemaParserRegistry registry;

    @Test
    public void testBasePackagesAreScanned() {
        registry = new SchemaParserRegistry();
        assertNotNull(registry.getParser(TEST_1));
        assertNotNull(registry.getParser(TEST_2));
        assertNull(registry.getParser(TYPE_BAD));
    }

    @Test
    public void testBadPackageReturnsNull() {
        registry = new SchemaParserRegistry();
        assertNull(registry.getParser(TYPE_BAD));
    }

    @Test(expected = SchemaParserException.class)
    public void testNull() {
        registry = new SchemaParserRegistry();
        registry.getParser(null);
    }

    @Test
    public void testAllConstructorsWithEmptyExtraPackages() {
        registry = new SchemaParserRegistry(new String[]{});
        assertNotNull(registry.getParser(TEST_1));
        assertNotNull(registry.getParser(TEST_2));
        assertNull(registry.getParser(TYPE_BAD));

        registry = new SchemaParserRegistry(new ArrayList<>());
        assertNotNull(registry.getParser(TEST_1));
        assertNotNull(registry.getParser(TEST_2));
        assertNull(registry.getParser(TYPE_BAD));
    }

    @Test
    public void testAllConstructorsWithExtraPackages() {
        String[] packages = new String[]{ MOCK_PACKAGE, NULLING_PACKAGE };

        registry = new SchemaParserRegistry(packages);
        assertNotNull(registry.getParser(TYPE_MOCK));
        assertNotNull(registry.getParser(TYPE_NULLING));
        assertNull(registry.getParser(TYPE_BAD));

        registry = new SchemaParserRegistry(Arrays.asList(packages));
        assertNotNull(registry.getParser(TYPE_MOCK));
        assertNotNull(registry.getParser(TYPE_NULLING));
        assertNull(registry.getParser(TYPE_BAD));
    }

    @Test
    public void testAllConstructorsWithExtraPackagesAndBadPackage() {
        String[] packages = new String[]{ MOCK_PACKAGE, NULLING_PACKAGE, BAD_PACKAGE };

        registry = new SchemaParserRegistry(packages);
        assertNotNull(registry.getParser(TYPE_MOCK));
        assertNotNull(registry.getParser(TYPE_NULLING));
        assertNull(registry.getParser(TYPE_BAD));

        registry = new SchemaParserRegistry(Arrays.asList(packages));
        assertNotNull(registry.getParser(TYPE_MOCK));
        assertNotNull(registry.getParser(TYPE_NULLING));
        assertNull(registry.getParser(TYPE_BAD));
    }

    @Test
    public void testAddPackage() {
        registry = new SchemaParserRegistry().addPackage(MOCK_PACKAGE).addPackage(NULLING_PACKAGE);
        assertNotNull(registry.getParser(TYPE_MOCK));
        assertNotNull(registry.getParser(TYPE_NULLING));
        assertNull(registry.getParser(TYPE_BAD));
    }

    @Test
    public void testAddPackageWithBadPackage() {
        registry = new SchemaParserRegistry().addPackage(MOCK_PACKAGE).addPackage(BAD_PACKAGE);
        assertNotNull(registry.getParser(TYPE_MOCK));
        assertNull(registry.getParser(TYPE_BAD));
    }

    @Test(expected = SchemaParserException.class)
    public void testEmptyTypeAnnotation() {
        registry = new SchemaParserRegistry(INVALID_PACKAGE);
    }

    @Test(expected = SchemaParserException.class)
    public void testClassIncorrectlyAnnotatedAsAParser() {
        registry = new SchemaParserRegistry(NOT_A_PARSER_PACKAGE);
    }

    @Test(expected = SchemaParserException.class)
    public void testDuplicateParsers() {
        registry = new SchemaParserRegistry(DUPLICATE_PACKAGE);
    }

    @Test(expected = SchemaParserException.class)
    public void testDuplicateParsersInDifferentPackages() {
        registry = new SchemaParserRegistry(new String[]{NULLING_PACKAGE, NULLING_DIFFERENT_PACKAGE});
    }

    @Test(expected = SchemaParserException.class)
    public void testDuplicateParsersAddedSeparately() {
        registry = new SchemaParserRegistry(NULLING_PACKAGE).addPackage(NULLING_DIFFERENT_PACKAGE);
    }

}
