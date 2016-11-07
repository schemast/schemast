package com.schemast.schemas;

import static com.schemast.Constants.MIN_VERSION;
import static com.schemast.Constants.VERSION;

public class Version {
	private final int version;

	public Version(int version) {
		if (version < MIN_VERSION)
			throw new InvalidSchemaException(VERSION + " must be " + MIN_VERSION + " or later");

		this.version = version;
	}

	public int get() {
		return version;
	}
}
