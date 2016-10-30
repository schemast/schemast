package com.schemast.schemas.fields;

import java.math.BigInteger;

public class BigIntegerField extends Field {
    private BigInteger defaultValue = null;

    public BigIntegerField(String name) {
        super(name);
    }

    public String getType() {
        return Field.BIG_INTEGER;
    }

    public BigIntegerField withDefault(BigInteger val) {
        this.defaultValue = val;
        return this;
    }

}
