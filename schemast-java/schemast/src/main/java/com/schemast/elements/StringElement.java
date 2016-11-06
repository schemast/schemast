package com.schemast.elements;

import java.util.Optional;

public class StringElement extends Element {
	public static final String NULLABLE = "nullable";
    public static final String DEFAULT = "default";
    public static final String MIN_LENGTH = "minLength";
    public static final String MAX_LENGTH = "maxLength";

    static class Builder extends ElementBuilder {
	    private boolean nullable = false;
        private NullableDefault<String> def = new NullableDefault<String>();
        private Long minLength;
        private Long maxLength;

        public Builder(String label) {
            super(label, Element.TYPE_STRING);
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

        public Builder minLength(Long minLength) {
            if (minLength == null) {
                throw new InvalidElementException(MIN_LENGTH + " cannot be null");
            } else if (minLength < 0L) {
                throw new InvalidElementException(MIN_LENGTH + " cannot be less than 0");
            } else {
                this.minLength = minLength;
                return this;
            }
        }

        public Builder maxLength(Long maxLength) {
            if (maxLength == null) {
                throw new InvalidElementException(MAX_LENGTH + " cannot be null");
            } else if (maxLength < 1L) {
                throw new InvalidElementException(MAX_LENGTH + " cannot be less than 1");
            } else {
                this.maxLength = maxLength;
                return this;
            }
        }

        @Override
        public StringElement build() {
	        if (def.wasSet()) {
		        if (!nullable && !def.getOpt().isPresent()) {
			        throw new InvalidElementException("Cannot set null " + DEFAULT + " when not " + NULLABLE);
		        }

		        def.getOpt().ifPresent(s -> {
			        if (minLength != null && s.length() < minLength)
				        throw new InvalidElementException(DEFAULT + " cannot be shorter than " + MIN_LENGTH);
			        if (maxLength != null && s.length() > maxLength)
				        throw new InvalidElementException(DEFAULT + " cannot be longer than " + MAX_LENGTH);
		        });
	        }

            if (minLength != null && maxLength != null && minLength > maxLength)
                throw new InvalidElementException(MIN_LENGTH + " cannot be greater than " + MAX_LENGTH);

            return new StringElement(label, type, optional, nullable, def, minLength, maxLength);
        }
    }

    private boolean nullable;
	private NullableDefault<String> def;
	private Long minLength;
	private Long maxLength;

    private StringElement(String label, String type, boolean optional, boolean nullable,
                          NullableDefault<String> def, Long minLength, Long maxLength) {
        super(label, type, optional);
	    this.nullable = nullable;
        this.def = def;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public boolean isNullable() {
	    return nullable;
    }

    public NullableDefault<String> getDefault() {
        return def;
    }

    public Optional<Long> getMinLength() {
        return Optional.of(minLength);
    }

	public Optional<Long> getMaxLength() {
		return Optional.of(maxLength);
    }
}
