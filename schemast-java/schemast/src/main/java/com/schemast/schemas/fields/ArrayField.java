package com.schemast.schemas.fields;

import java.util.ArrayList;
import java.util.List;

public class ArrayField extends Field {
    private List<Field> fields = new ArrayList<>();

    public ArrayField(String name) {
        super(name);
    }

    public String getType() {
        return Field.ARRAY;
    }

    public ArrayField addField(Field field) {
        fields.add(field);
        return this;
    }

}
