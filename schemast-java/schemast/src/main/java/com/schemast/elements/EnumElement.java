package com.schemast.elements;

import com.schemast.util.NullableDefault;

import java.util.HashSet;
import java.util.Set;

import static com.schemast.Constants.TYPE_BOOLEAN;
import static com.schemast.Constants.TYPE_ENUM;

public class EnumElement extends Element {
	public static final String NULLABLE = "nullable";
	public static final String DEFAULT = "default";

	public static class Builder extends ElementBuilder {
	    private boolean nullable = false;
        private NullableDefault<String> def = new NullableDefault<>();
	    private Set<String> values = new HashSet<>();

        public Builder(String name) {
            super(name, TYPE_BOOLEAN);
        }

        @Override
        public Builder optional(Boolean optional) {
            super.optional(optional);
            return this;
        }

	    public Builder nullable(Boolean nullable) {
		    if (nullable == null) throw new InvalidElementException(NULLABLE + " cannot be null");
		    this.nullable = nullable;
		    return this;
	    }

        public Builder withDefault(String def) {
            this.def.set(def);
            return this;
        }

		public Builder add(String value) {
			if (value == null) {
				throw new InvalidElementException("Cannot add a null value to " + TYPE_ENUM);
			} else if (values.add(value)) {
				return this;
			} else {
				throw new InvalidElementException("Duplicate value in " + TYPE_ENUM + ": " + value);
			}
		}

        @Override
        public EnumElement build() {
	        if (def.wasSet()) {
		        if (def.getOpt().isPresent()) {
			        if (!values.contains(def.getOpt().get())) {
				        throw new InvalidElementException(DEFAULT + " value is not a member of " + TYPE_ENUM + " " + name);
			        }
		        } else if (!nullable) {
			        throw new InvalidElementException("Cannot set null " + DEFAULT + " when not " + NULLABLE);
		        }
	        }

	        if (values.size() < 2) {
		        throw new InvalidElementException(TYPE_ENUM + " requires at least two values");
	        }

            return new EnumElement(name, type, optional, nullable, def, values);
        }
    }

	private final boolean nullable;
	private final NullableDefault<String> def;
	private final Set<String> values;

    private EnumElement(String name, String type, boolean optional, boolean nullable,
                        NullableDefault<String> def, Set<String> values) {
	    super(name, type, optional);
	    this.nullable = nullable;
	    this.def = def;
	    this.values = values;
    }

	public boolean isNullable() {
		return nullable;
	}

	public NullableDefault<String> getDefault() {
		return def;
	}

	public Set<String> getValues() {
		return values;
	}
}
