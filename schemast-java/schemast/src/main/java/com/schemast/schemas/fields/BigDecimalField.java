package com.schemast.schemas.fields;

import java.math.BigDecimal;

public class BigDecimalField extends Field implements Defaulted<BigDecimal> {
    private BigDecimal defaultValue = null;

    public BigDecimalField(String name) {
        super(name);
    }

    public String getType() {
        return Field.BIG_DECIMAL;
    }

    @Override
    public BigDecimalField withDefault(BigDecimal val) {
        this.defaultValue = val;
        return this;
    }

    @Override
    public BigDecimal getDefault() {
        return defaultValue;
    }

}
