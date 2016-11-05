package com.schemast.schemas.elements.fields;

import com.schemast.schemas.elements.Element;
import com.schemast.schemas.elements.FieldElement;

import static com.schemast.schemas.elements.FieldElement.SEARCH;
import static com.schemast.schemas.elements.FieldElement.STORE;

public abstract class FieldBuilder {
    protected String label;
    protected FieldElement.Search search = FieldElement.Search.NO;
    protected FieldElement.Store store = FieldElement.Store.YES;
    protected boolean required = false;
    protected boolean nullable = true;

    FieldBuilder(String label) {
        this.label = label;
    }

    protected abstract Element doFieldBuild();

    public FieldBuilder search(FieldElement.Search search) {
        this.search = search ;
        return this;
    }

    public FieldBuilder stored(FieldElement.Store store) {
        this.store = store;
        return this;
    }

    public FieldBuilder required(boolean required) {
        this.required = required;
        return this;
    }

    public FieldBuilder nullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public Element build() {
        if (search == null) {
            throw new InvalidFieldException(SEARCH + " cannot be null");
        } else if (store == null) {
            throw new InvalidFieldException(STORE + " cannot be null");
        } else {
            return doFieldBuild();
        }
    }

}
