package com.schemast.schemas;

import com.schemast.util.Name;
import com.schemast.elements.Element;
import com.schemast.util.Appender;
import com.schemast.util.Named;

import java.util.Collection;
import java.util.Collections;

import static com.schemast.Constants.METADATA;
import static com.schemast.Constants.STRUCTURE;

public class Schema extends Named {

	public static class Builder {
		private final String namespace;
		private Metadata metadata;
		private Appender<Element> elements = new Appender<>();

		public Builder(String namespace) {
			this.namespace = namespace;
		}

		public Builder metadata(Metadata metadata) {
			this.metadata = metadata;
			return this;
		}

		public Builder append(Element s) {
			elements.append(s);
			return this;
		}

		public Schema build() {
			if (metadata == null) {
				throw new InvalidSchemaException(METADATA + " cannot be null");
			} else if (elements.isEmpty()) {
				throw new InvalidSchemaException(STRUCTURE + " must contain at least one element");
			} else {
				return new Schema(namespace, metadata, elements.getItems());
			}
		}
	}

	private final Name namespace;
	private final Metadata metadata;
	private final Collection<Element> elements;

	private Schema(String namespace, Metadata metadata, Collection<Element> elements) {
	    super(metadata.getName());
	    this.namespace = new Name(namespace);
        this.metadata = metadata;
		this.elements = Collections.unmodifiableCollection(elements);
	}

	public String getNamespace() {
		return namespace.get();
	}

	public Metadata getMetadata() {
        return metadata;
    }

	public Collection<Element> getElements() {
		return elements;
	}
}
