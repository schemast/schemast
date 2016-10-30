package com.schemast.schemas.fields;

import java.math.BigDecimal;

public class BigDecimalField extends Field {
    private BigDecimal defaultValue = null;

    public BigDecimalField(String name) {
        super(name);
    }

    public String getType() {
        return Field.BIG_DECIMAL;
    }

    public BigDecimalField withDefault(BigDecimal val) {
        this.defaultValue = val;
        return this;
    }

}
