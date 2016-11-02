package com.schemast.schemas.fields;

import java.math.BigInteger;

public class BigIntegerField extends Field implements Defaulted<BigInteger> {
    private BigInteger defaultValue = null;

    public BigIntegerField(String name) {
        super(name);
    }

    public Field.Type getType() {
        return Type.BIG_INTEGER;
    }

    @Override
    public BigIntegerField withDefault(BigInteger val) {
        this.defaultValue = val;
        return this;
    }

    @Override
    public BigInteger getDefault() {
        return defaultValue;
    }

}
