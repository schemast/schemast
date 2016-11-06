package test;

import com.schemast.elements.Element;
import com.schemast.schemas.Header;
import com.schemast.schemas.Schema;

public class TestData {
    protected static final String NAMESPACE = "myNamespace";
    protected static final String NAMESPACE2 = "myNamespace2";
    protected static final String NAME = "mySchema";
    protected static final String NAME2 = "mySchema2";
    protected static final int VERSION = 4;
    protected static final int VERSION2 = 5;
    protected static final String LABEL = "myField";
    protected static final String LABEL2 = "myField2";

    protected Header testHeader() {
        return testHeader(NAMESPACE, NAME, VERSION);
    }

    protected Header testHeader(String namespace, String name, int version) {
        return new Header(namespace, name, version);
    }

    protected Schema testSchema() {
        return testSchema(NAMESPACE, NAME, VERSION);
    }

    protected Schema testSchema(String namespace, String name, int version) {
        Header h = testHeader(namespace, name, version);
        return new Schema(h).append(mockElement());
    }

    protected Element mockElement() {
        return mockElement(LABEL);
    }

    protected Element mockElement(String label) {
        return new MockElement(label);
    }

//    protected final List<Element> allFields = null; //TODO: buildAllFields();
//
//    protected Element booleanField() {
//        return booleanField(FIELD);
//    }
//
//    protected Element booleanField(String label) {
//        return booleanFieldBuilder(label).build();
//    }
//
//    protected BooleanElement.Builder booleanFieldBuilder(String label) {
//        return new BooleanElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    protected Element decimalField() {
//        return decimalField(FIELD);
//    }
//
//    protected Element decimalField(String label) {
//        return decimalFieldBuilder(label).build();
//    }
//
//    protected DecimalFieldElement.Builder decimalFieldBuilder(String label) {
//        return new DecimalFieldElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    protected Element intField() {
//        return intField(FIELD);
//    }
//
//    protected Element intField(String label) {
//        return intFieldBuilder(label).build();
//    }
//
//    protected IntElement.Builder intFieldBuilder(String label) {
//        return new IntElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    protected Element stringField() {
//        return stringField(FIELD);
//    }
//
//    protected Element stringField(String label) {
//        return stringFieldBuilder(label).build();
//    }
//
//    protected StringElement.Builder stringFieldBuilder(String label) {
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
