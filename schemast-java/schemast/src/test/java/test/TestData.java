package test;

import com.schemast.schemas.Metadata;
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

    protected Metadata testMetadata() {
        return new Metadata.Builder().namespace(NAMESPACE).name(NAME).version(VERSION).build();
    }

    protected Schema testSchema() {
        return testSchema(NAMESPACE, NAME, VERSION);
    }

    protected Schema testSchema(String namespace, String name, int version) {
        Metadata m = new Metadata.Builder().name(name).namespace(namespace).version(version).build();
        return new Schema.Builder().metadata(m).append(new MockElement(LABEL)).build();
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
//    protected BooleanFieldElement.Builder booleanFieldBuilder(String label) {
//        return new BooleanFieldElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
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
//    protected IntFieldElement.Builder intFieldBuilder(String label) {
//        return new IntFieldElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
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
//    protected StringFieldElement.Builder stringFieldBuilder(String label) {
//        return new StringFieldElement.Builder(label).nullable(true).required(false).stored(FieldElement.Store.YES).search(FieldElement.Search.NO);
//    }
//
//    private List<Element> buildAllFields() {
//        List<Element> fields = new ArrayList<>();
//        fields.add(new BooleanFieldElement.Builder(SchemaParserBaseTest.BOOLEAN_FIELD_NAME).build());
//        fields.add(new DecimalFieldElement.Builder(SchemaParserBaseTest.DECIMAL_FIELD_NAME).build());
//        fields.add(new IntFieldElement.Builder(SchemaParserBaseTest.INTEGER_FIELD_NAME).build());
//        fields.add(new StringFieldElement.Builder(SchemaParserBaseTest.STRING_FIELD_NAME).build());
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
//        //TODO:
//        return null;
//    }

}
