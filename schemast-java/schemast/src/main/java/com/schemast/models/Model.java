package com.schemast.models;

import com.schemast.schemas.Schema;
import com.schemast.util.Appender;

import java.util.Collection;
import java.util.Collections;

import static com.schemast.Constants.HEADER;
import static com.schemast.Constants.SCHEMAS;

public class Model {

	public static class Builder {
		private Header header;
		private Appender<Schema> schemas = new Appender<>();

		public Builder header(Header header) {
			this.header = header;
			return this;
		}

		public Builder append(Schema s) {
			schemas.append(s);
			return this;
		}

		public Model build() {
			if (header == null) {
				throw new InvalidModelException(HEADER + " cannot be null");
			} else if (schemas.isEmpty()) {
				throw new InvalidModelException(SCHEMAS + " must contain at least one schema");
			} else {
				return new Model(header, schemas.getItems());
			}
		}
	}

	private final Header header;
	private final Collection<Schema> schemas;

	private Model(Header header, Collection<Schema> schemas) {
		this.header = header;
		this.schemas = Collections.unmodifiableCollection(schemas);
	}

	public Header getHeader() {
		return header;
	}

	public Collection<Schema> getSchemas() {
		return schemas;
	}
}
