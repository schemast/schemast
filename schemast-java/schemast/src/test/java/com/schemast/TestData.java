package com.schemast;

import com.schemast.schemas.SchemaParserBaseTest;
import com.schemast.schemas.fields.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestData {
    protected final List<Field> allFields = buildAllFields();

    private List<Field> buildAllFields() {
        List<Field> fields = new ArrayList<>();
        fields.add(new BigDecimalField(SchemaParserBaseTest.BIG_DECIMAL_FIELD_NAME));
        fields.add(new BigIntegerField(SchemaParserBaseTest.BIG_INTEGER_FIELD_NAME));
        fields.add(new BooleanField(SchemaParserBaseTest.BOOLEAN_FIELD_NAME));
        fields.add(new DecimalField(SchemaParserBaseTest.DECIMAL_FIELD_NAME));
        fields.add(new FloatField(SchemaParserBaseTest.FLOAT_FIELD_NAME));
        fields.add(new IntegerField(SchemaParserBaseTest.INTEGER_FIELD_NAME));
        fields.add(new LongField(SchemaParserBaseTest.LONG_FIELD_NAME));
        fields.add(new StringField(SchemaParserBaseTest.STRING_FIELD_NAME));
        return fields;
    }

    public List<Field> buildAllBasicPermutations(Field f) {
        List<Field> perms = new ArrayList<>();

        for (Field.Index i : Field.Index.values()) {
            for (Field.Store s : Field.Store.values()) {
                IntStream.range(0,2).forEach(nu -> {
                    IntStream.range(0,2).forEach(req -> {
                        Field clone = (Field) f.clone();
                        clone.setIndex(i).setStored(s).setNullable(nu != 0).setRequired(req != 0);
                        perms.add(clone);
                    });
                });
            }
        }

        return perms;
    }

}
