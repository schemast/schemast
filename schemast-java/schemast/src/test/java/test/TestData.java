package test;

import com.schemast.elements.Element;
import com.schemast.models.Header;
import com.schemast.schemas.Metadata;
import com.schemast.schemas.Schema;

public class TestData {
    public static final String NAMESPACE = "myNamespace";
    public static final String NAMESPACE2 = "myNamespace2";
    public static final String NAME = "mySchema";
    public static final String NAME2 = "mySchema2";
    public static final int VERSION = 4;
    public static final int VERSION2 = 5;
    public static final String FIELD_NAME = "myField";
    public static final String FIELD_NAME2 = "myField2";

    public Header testHeader() {
        return testHeader(NAMESPACE);
    }

    public Header testHeader(String namespace) {
        return new Header(namespace);
    }

    public Metadata testMetadata() {
        return testMetadata(NAME,VERSION);
    }

    public Metadata testMetadata(String name, int version) {
        return new Metadata.Builder().name(name).version(version).build();
    }

    public Schema testSchema() {
        return testSchema(NAMESPACE, NAME, VERSION);
    }

    public Schema testSchema(String namespace, String name, int version) {
        return new Schema.Builder(namespace).metadata(testMetadata(name, version)).append(mockElement()).build();
    }

    public Element mockElement() {
        return mockElement(FIELD_NAME);
    }

    public Element mockElement(String name) {
        return new MockElement(name);
    }

//    public final List<Element> allFields = null; //TODO: buildAllFields();
//
//    public Element booleanField() {
//        return booleanField(FIELD);
//    }
//
//    public Element booleanField(String label) {
//        return booleanFieldBuilder(label).build();
//    }
//
//    public BooleanElement.Builder booleanFieldBuilder(String label) {
//        return new BooleanElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    public Element decimalField() {
//        return decimalField(FIELD);
//    }
//
//    public Element decimalField(String label) {
//        return decimalFieldBuilder(label).build();
//    }
//
//    public DecimalFieldElement.Builder decimalFieldBuilder(String label) {
//        return new DecimalFieldElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    public Element intField() {
//        return intField(FIELD);
//    }
//
//    public Element intField(String label) {
//        return intFieldBuilder(label).build();
//    }
//
//    public IntElement.Builder intFieldBuilder(String label) {
//        return new IntElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    public Element stringField() {
//        return stringField(FIELD);
//    }
//
//    public Element stringField(String label) {
//        return stringFieldBuilder(label).build();
//    }
//
//    public StringElement.Builder stringFieldBuilder(String label) {
//        return new StringElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    private List<Element> buildAllFields() {
//        List<Element> fields = new ArrayList<>();
//        fields.add(new BooleanElement.Builder(SchemaParserBaseTest.BOOLEAN_FIELD_NAME).build());
//        fields.add(new DecimalFieldElement.Builder(SchemaParserBaseTest.DECIMAL_FIELD_NAME).build());
//        fields.add(new IntElement.Builder(SchemaParserBaseTest.INTEGER_FIELD_NAME).build());
//        fields.add(new StringElement.Builder(SchemaParserBaseTest.STRING_FIELD_NAME).build());
//        return fields;
//    }
//
//    public List<Element> buildAllBasicPermutations(FieldElement.Builder feb) {
//        List<Element> perms = new ArrayList<>();
//
//        for (FieldElement.Search i : FieldElement.Search.values()) {
//            for (FieldElement.Store s : FieldElement.Store.values()) {
//                IntStream.range(0,2).forEach(nu -> {
//                    IntStream.range(0,2).forEach(req -> {
//                        feb.search(i).setStored(s).setNullable(nu != 0).setRequired(req != 0);
//                        perms.add(clone);
//                    });
//                });
//            }
//        }
//
//        return perms;
//    }

}
